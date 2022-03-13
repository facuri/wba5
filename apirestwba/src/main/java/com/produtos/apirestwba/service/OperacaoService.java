package com.produtos.apirestwba.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.produtos.apirestwba.model.DataDTO;
import com.produtos.apirestwba.model.Operacao;
import com.produtos.apirestwba.model.Titulo;
import com.produtos.apirestwba.repository.OperacaoRepository;
import com.produtos.apirestwba.repository.TituloRepository;
 

@Service
public class OperacaoService {
	
	@Autowired
	OperacaoRepository operacaoRepository;
	
    public List<Operacao> listtAll(){
		
        return operacaoRepository.findAll();
	 
	}
    public Operacao getOp(Long id) {
	 
	   return operacaoRepository.findById(id).get();
 }
     public Operacao saveOp(Operacao operacao) {
	  
	     long dias;
	     SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	  
	     Date dataOperacao = operacao.getDataOperacao(); 
	     dataOperacao = new Date();
	     LocalDate userDataOperacao = dataOperacao.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		 
	     for(int pos = 0; pos < operacao.getTitulos().size(); pos++) {
		  
		     operacao.getTitulos().get(pos).setOperacao(operacao);
		    
		     operacao.setValorTotal(operacao.getValorTotal().add(operacao.getTitulos().get(pos).getValor()));
		   
		     Date dataVencimento =  operacao.getTitulos().get(pos).getDataVencimento();
		     dataVencimento = new Date();
			 LocalDate userDataVencimento = dataVencimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		   
		     formato.format(dataOperacao);
		     formato.format(dataVencimento);
		   
		     dias = ChronoUnit.DAYS.between(userDataOperacao,userDataVencimento);
		     operacao.getTitulos().get(pos).setPrazo(BigDecimal.valueOf(dias));
		    
		       
		 }  
	  
	  return  operacaoRepository.save(operacao);
	  
	}
 
    public Operacao updateOp(Operacao operacao) {
	  
	     long dias;
	     SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	  
	     Date dataOperacao = operacao.getDataOperacao(); 
	     dataOperacao = new Date();
	     LocalDate userDataOperacao = dataOperacao.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		 
	     for(int pos = 0; pos < operacao.getTitulos().size(); pos++) {
		  
		     operacao.getTitulos().get(pos).setOperacao(operacao);
		    
		     operacao.setValorTotal(operacao.getValorTotal().add(operacao.getTitulos().get(pos).getValor()));
		   
		     Date dataVencimento =  operacao.getTitulos().get(pos).getDataVencimento();
		     dataVencimento = new Date();
			 LocalDate userDataVencimento = dataVencimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		   
		     formato.format(dataOperacao);
		     formato.format(dataVencimento);
		   
		     dias = ChronoUnit.DAYS.between(userDataOperacao,userDataVencimento);
		     operacao.getTitulos().get(pos).setPrazo(BigDecimal.valueOf(dias));
		    
		       
		 }  
	  
	  return  operacaoRepository.save(operacao);
 

 }
 
   public void deleteOp(Long id) {
	 
	    operacaoRepository.deleteById(id);
  }
 
 
 }
