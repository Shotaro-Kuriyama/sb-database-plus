package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.FoodDao;


@Controller
public class DBController {

	// foodDao という変数名で FoodDao インターフェースを利用するための宣言
	@Autowired
	private FoodDao foodDao;
	
	// 検索画面の表示
	//URL「http://localhost:8080/db/search」とひもづけする
	@GetMapping("/db/search")
	public ModelAndView showSearchForm(ModelAndView mav) {
		//次に表示させるHTMLファイル名を指定する文
		mav.setViewName("search");
		return mav;
	}
	
	// 検索結果の表示
	//URL「http://localhost:8080/db/result」とひもづけする
	@GetMapping("/db/result")
	//リクエストとして送られてきた値 searchWord を引数「String searchWord」に格納する
	public ModelAndView search(@RequestParam("searchWord") String searchWord, ModelAndView mav) {
		// searchメソッドの処理内容
		ArrayList<String> foodNameList = foodDao.selectFoodName(searchWord);
		mav.addObject("foodNameList",foodNameList);
		mav.addObject("searchWord", searchWord);
		
		mav.setViewName("result");
		
		return mav;
	}
	
	
	// 1, 登録画面を表示する
	@GetMapping("/db/add")
	public ModelAndView showAddForm(ModelAndView mav) {
		
		mav.setViewName("add");
		return mav;
		
	}
		
	// 2, 登録ボタンを押した後の処理内容
	@GetMapping("/db/result2")
	
	public ModelAndView add(
			@RequestParam("addCategory") String addCategory, 
			@RequestParam("addFood") String addFood, 
			ModelAndView mav) {
		//　上のプログラムで入力された値を取得した
		//　下のプログラムで登録する処理を呼び出す(DAOのinsertメソッドを呼び出す)
		foodDao.addFood(addCategory, addFood);
		
		// 必要であれば画面に値を渡す
        mav.addObject("category", addCategory);
        mav.addObject("food", addFood);

		
		mav.setViewName("result2");
		return mav;
		
		
	}

}
