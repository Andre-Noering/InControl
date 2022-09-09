package com.entra21.LojaSimulator.controller;

import com.entra21.LojaSimulator.view.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemRestController {
	@Autowired
	private ItemService itemService;
}
