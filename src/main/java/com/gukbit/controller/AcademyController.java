package com.gukbit.controller;

import com.gukbit.dto.AcademyDto;
import com.gukbit.service.AcademyService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/academy")
public class AcademyController {

  private final AcademyService academyService;

  public AcademyController(AcademyService academyService) {
    this.academyService = academyService;
  }

  @GetMapping({"", "/"})
  String academyMapping(Model model) {
    List<String> items = new ArrayList<>();
    items.add("강사진");
    items.add("커리큘럼");
    items.add("취업 연계");
    items.add("학원 내 문화");
    items.add("운영 및 시설");
    model.addAttribute("items", items);
    return "/view/academy";
  }

  @GetMapping("/search")
  public String searchAcademy(@RequestParam(value = "keyword") String keyword, Model model) {
      List<AcademyDto> academyDtoList = academyService.searchAcademy(keyword);
      model.addAttribute("academyList", academyDtoList);
      model.addAttribute("keyword", keyword);
      return "/view/searchAcademy";
  }
}