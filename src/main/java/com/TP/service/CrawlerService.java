package com.TP.service;

import com.TP.entity.*;
import com.TP.helper.Helper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.swing.*;
import java.io.*;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Service
@Scope("prototype")
public class CrawlerService extends Thread {
    @Autowired
    DanhMucService danhMucService;
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    MauSanPhamService mauSanPhamService;
    @Autowired
    SizeSanPhamService sizeSanPhamService;

    public CrawlerService() {

    }

    public String scrape() {
        String driverPath = "D:\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        String pageSoure = "";
        ChromeDriver driver = new ChromeDriver();
        try {
            driver.get("https://yame.vn/");
            pageSoure = driver.getPageSource();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Kiểm tra kết nối internet");
        } finally {
            driver.quit();
        }


        return pageSoure;

    }

    public void getDataProductByCategories(Element categories, int parentId) throws IOException {
//		              Duyệt sản phẩm trong danh mục con
        for (Element category : categories.select("li")) {
            String subCategory = category.text();
            DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
            danhMucSanPham.setTendanhmuc(subCategory);
            danhMucSanPham.setParent_madanhmuc(parentId);
            int maDanhmuc = danhMucService.save2(danhMucSanPham);
            danhMucSanPham.setMadanhmuc(maDanhmuc);

//          	Link truy cập danh mục con
            String linkCategory = category.select("a[href").first().attr("href");

            int numberPage = 1;
            while (true) {
                Document yameCategory = Jsoup.connect("https://yame.vn" + linkCategory + "&page=" + numberPage).timeout(45000)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
                        .get();

//          		   lấy danh sách sản phẩm trong danh mục
                Elements products = yameCategory.select("div.pitem");
                for (Element product : products) {
                    try {
                        CookieHandler.setDefault(new CookieManager());
                        String linkProductByCategogy = product.select("a[href]").attr("href");
                        Document yameProduct = Jsoup.connect("https://yame.vn" + linkProductByCategogy).timeout(45000)
                                .ignoreHttpErrors(true)
                                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
                                .get();
                        Elements elVariants = yameProduct.select("tbody > tr");
                        String title = yameProduct.select(".display-5 > span").first().text();
                        String img = yameProduct.select(".img-fluid").attr("src");
                        String tenhinh = title.replace(" ", "-").toLowerCase();
                        tenhinh += tenhinh + ".jpg";
                        String mota = yameProduct.select(".ditem > .row > .col-md-4").html();
                        Elements prices = yameProduct.select("h5.price");
                        String price = "";
//              		  Xử lý tiền( chỉ lấy giá gốc)
                        if (prices.size() > 1) {
                            price = prices.get(1).text();
                        } else {
                            price = prices.get(0).text();
                        }
                        price = price.replace(",", ".");
                        price = price.replace("đ", "");

                        Set<ChiTietSanPham> chiTietSanPhams = new HashSet<ChiTietSanPham>();
                        for (Element elVariant : elVariants) {
                            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
//              			  Lấy màu + size + số lượng
                            String mauSize = elVariant.select("td").get(0).text();
                            String mS[] = mauSize.split(",");
                            String mau = mS[0];
                            String size = mS[1];
                            String soluong = elVariant.select("td").get(1).text();
                            soluong = soluong.split(" ")[0];
                            MauSanPham mauSanPham = new MauSanPham();
                            mauSanPham.setTenmau(mau);
                            int mamau = mauSanPhamService.save(mauSanPham);
                            mauSanPham.setMamau(mamau);


                            chiTietSanPham.setMauSanPham(mauSanPham);

                            SizeSanPham sizeSanPham = new SizeSanPham();
                            sizeSanPham.setSize(size);
                            int masize = sizeSanPhamService.save(sizeSanPham);
                            sizeSanPham.setMasize(masize);

                            chiTietSanPham.setSizeSanPham(sizeSanPham);
                            if (soluong.equals("Hết")) {
                                chiTietSanPham.setSoluong(0);
                            } else {
                                chiTietSanPham.setSoluong(Integer.valueOf(soluong));
                            }
                            chiTietSanPhams.add(chiTietSanPham);

                        }
//              		thông tin=title+ "  ___"+price+"__"+img +"\n" +mota;
                        SanPham sanPham = new SanPham();
                        sanPham.setTensanpham(title);
                        sanPham.setGiatien(price);
                        sanPham.setDanhcho("Nam");
                        sanPham.setMota(mota);
                        sanPham.setHinhsanpham(tenhinh);
                        sanPham.setDanhMucSanPham(danhMucSanPham);
                        sanPham.setChiTietSanPham(chiTietSanPhams);
                        sanPhamService.save2(sanPham);
                        saveImg(img, tenhinh);
                    } catch (Exception e) {

                    }
                }
//              	  Kiểm tra còn trang kê tiếp không
                Element nextPage = yameCategory.select(".PagedList-skipToNext").first();
                if (nextPage == null) {
                    break;
                }
                numberPage++;
            }

        }
    }


    public String CrawlerData() throws IOException {
        Document yameHome = Jsoup.parse(scrape());
//		Lấy danh sách danh mục cha
        Elements parentCategories = yameHome.select("ul.site-nav-wrap >li.has-children > div.dropdown > div.d-flex > div.p-3");
        int i = 1;
        for (Element categories : parentCategories) {
//        	Lấy danh mục gốc
            String parentCategory = categories.select("a").first().text();
            DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
            danhMucSanPham.setTendanhmuc(parentCategory);
            int parentId = danhMucService.save2(danhMucSanPham);
//           Duyệt sản phẩm trong danh mục con
            getDataProductByCategories(categories, parentId);
            i++;
//            Lấy tới danh mục cần thiết
            if (i == 10) {
                break;
            }


        }
        return "Succes Crawler Data";
    }

    private void saveImg(String src_image, String name) {
        try {
        	String path_save_file = Helper.getPathSaveImg();
//            String path_save_file = context.getRealPath("/resources/images/sanpham/");
            URL url = new URL(src_image);
            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(path_save_file + "/" + name));
            for (int b; (b = in.read()) != -1; ) {
                out.write(b);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not Dowload File !");
        }
    }

    @Override
    public void run() {
        System.out.println("Start thread crawler:");


        try {
            CrawlerData();

        } catch (Exception exception) {
//                System.out.println(exception.getMessage());
            JOptionPane.showMessageDialog(null, "Something wrong !");
        }
        JOptionPane.showMessageDialog(null, "End crawler !");


    }
}
