package net.kdigital.skyscrapper.controller;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.skyscrapper.domain.Product;
import net.kdigital.skyscrapper.service.ProductService;
import net.kdigital.skyscrapper.util.FileService;
import net.kdigital.skyscrapper.util.PageNavigator;

@Controller
@RequestMapping("/category")
@Slf4j
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Value("${user.board.page}")
	int countPerPage;

	@Value("${user.board.group}")
	int pagePerGroup;

	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	
	/**
	 * bidet 리스트 보여주기
	 * @return
	 */
	@GetMapping("/listbidet")
	public String category(
			@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value="searchItem", defaultValue="product_nm") String searchItem,
			@RequestParam(value="searchWord", defaultValue="") String searchWord,
			Model model) {
		
		int totalRecordCount = productService.getProductCount(searchItem, searchWord);
		
		PageNavigator navi= new PageNavigator(countPerPage, pagePerGroup, currentPage, totalRecordCount);
		
		log.info("{},{}",searchWord,searchItem);
		
		List<Product> listproduct = productService.selectProductAll(
				navi.getStartRecord(),
				navi.getEndRecord(),
				searchItem,
				searchWord);
		
		//log.info("글 목록 : {}",listproduct);
		
		model.addAttribute("totalRecordCount", totalRecordCount);
		model.addAttribute("listproduct", listproduct);
		model.addAttribute("navi", navi);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		
		
		return "/category/listbidet";
	}
	
	/**
	 * 상품등록 페이지 이동
	 * @return
	 */
	@GetMapping("/insertproduct")
	public String insertProduct() {
		return "/category/insertproduct";
	}
	
	/**
	 * 게시글 db 등록 요청(+사진)
	 * @param product 제품 도메인
	 * @param upload 파일 업로드
	 * @param model  
	 * @return
	 */
	@PostMapping("/insertproduct")
	public String insertProduct(Product product,
			MultipartFile upload,
			Model model) {
		
		String originalfile=null;
		String savedFileName=null;
	
		
		if(!upload.isEmpty()) {
			originalfile = upload.getOriginalFilename();
			savedFileName = FileService.saveFile(upload, uploadPath);
		}	

		product.setOriginalfile(originalfile);
		product.setSavedfilename(savedFileName);

		
		int result = productService.insertProduct(product); // 데이터 입력

		return "redirect:/category/listbidet";
		
		
	}
	
	/**
	 * 제품 상세보기에서 사용할 글
	 * @param product_id 조회할 글의 id
	 * @param model
	 * @return
	 */
	@GetMapping("/readproduct")
	public String readboard(int product_id,Model model) {
		//product_id에 해당하는 글 조회
		Product product = productService.selectProduct(product_id);
		log.info(product.toString());
		model.addAttribute("product", product);
		
		return "/category/readproduct";
	}
	
	@GetMapping("/download")
	public String download(int product_id, HttpServletResponse response) {
		Product product = productService.selectProduct(product_id);

		String originalFileName = product.getOriginalfile();
		String savedFileName = product.getSavedfilename();

		// Header를 세팅
		try {
			response.setHeader("Content-Disposition", 
					"attachment;filename="+URLEncoder.encode(originalFileName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String fullPath = uploadPath + "/" + savedFileName;

		// 스트림 설정(HDD입력, 클라이언트에게 출력)
		FileInputStream filein = null;
		ServletOutputStream fileout = null;

		try {
			filein = new FileInputStream(fullPath);

			fileout = response.getOutputStream();

			FileCopyUtils.copy(filein, fileout);

			fileout.close();
			filein.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
