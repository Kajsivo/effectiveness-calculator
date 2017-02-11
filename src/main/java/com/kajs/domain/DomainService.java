package com.kajs.domain;

import com.kajs.api.error.SheetNotFoundException;
import com.kajs.domain.model.Domain;
import com.kajs.domain.repository.DomainRepository;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class DomainService extends ExcelFileService {
    private static final Logger logger = LoggerFactory.getLogger(DomainService.class);
    private final DomainRepository domainRepository;

    @Autowired
    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public void saveDomains(MultipartFile domainsFile) {
        validateFile(domainsFile);
        Optional<Sheet> firstGlobalSheet = Optional.ofNullable(prepareSheet(domainsFile, 0));

        if(firstGlobalSheet.isPresent() ) {
            logger.info("Start processing first sheet");
            Sheet globalSheet = firstGlobalSheet.get();

            globalSheet.rowIterator().forEachRemaining((row) -> {
                Domain domain = new Domain();
                domain.setName(getCell(row, 0));
                domainRepository.save(domain);
            });
        } else {
            throw new SheetNotFoundException();
        }
    }

    public void addStartDateToDomain(MultipartFile startDateFile) {
        validateFile(startDateFile);
        Optional<Sheet> firstGlobalSheet = Optional.ofNullable(prepareSheet(startDateFile, 0));

        if(firstGlobalSheet.isPresent() ) {
            logger.info("Start processing first sheet");
            Sheet globalSheet = firstGlobalSheet.get();

            globalSheet.rowIterator().forEachRemaining((row) -> {
                Domain domain = new Domain();
                domain.setName(getCell(row, 0));
                domain.setStartDate((getCell(row, 1)));
                domainRepository.save(domain);
            });
        } else {
            throw new SheetNotFoundException();
        }



    }

}
