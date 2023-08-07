package com.ufop.HelpSind.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.ufop.HelpSind.dao.ReportDao;
import com.ufop.HelpSind.domain.Condominium;
import com.ufop.HelpSind.domain.Report;
import com.ufop.HelpSind.service.ReportService;
import com.ufop.HelpSind.service.ReportService;
import com.ufop.HelpSind.service.UserService;

@Service
@Transactional
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private UserService userService;

	@Override
	public void save(Report report) {
		if (report.getIdReport() == null) {
			report.setCondominium(userService.logged().getCondominium());
			reportDao.save(report);
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Report read(Long id) {
		return reportDao.findById(id).get();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Report> list() {
		Condominium condominium = userService.logged().getCondominium();
		if (condominium == null) {
			return new ArrayList<Report>();
		}
		return condominium.getPeople();
	}

	@Override
	public Page<Report> listPage(Pageable page) {
		Condominium condominium = userService.logged().getCondominium();
		if (condominium == null) {
			return Page.empty(page);
		}
		return reportDao.findAllByCondominiumOrderByName(condominium, page);
	}

	@Override
	public void update(Report report) {
		reportDao.save(report);
	}

	@Override
	public void delete(Report report) {
		reportDao.delete(report);		
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void validate(Report report, BindingResult validation) {
		
		if(report.getIdReport() == null) {
			if (report.getCpf() != null && reportDao.existsByCpfAndCondominium(report.getCpf(), userService.logged().getCondominium())) {
				validation.rejectValue("cpf", "Unique");
			}
		} else {
			if (report.getCpf() != null && reportDao.existsByCpfAndCondominiumAndIdReportNot(report.getCpf(), userService.logged().getCondominium(), report.getIdReport())) {
				validation.rejectValue("cpf", "Unique");
			}
		}
	}
	

}
