package com.entra21.LojaSimulator.view.service;

import com.entra21.LojaSimulator.view.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;
}
