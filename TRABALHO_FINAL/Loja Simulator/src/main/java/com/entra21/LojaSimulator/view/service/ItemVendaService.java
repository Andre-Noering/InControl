package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.view.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemVendaService {
    @Autowired
    private ItemVendaRepository itemVendaRepository;
}
