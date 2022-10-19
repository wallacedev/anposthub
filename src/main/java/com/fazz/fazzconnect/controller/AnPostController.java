package com.fazz.fazzconnect.controller;

import com.fazz.fazzconnect.gateway.AnPostGateway;
import com.fazz.fazzconnect.gateway.model.Address;
import com.fazz.fazzconnect.gateway.model.Consignment;
import com.fazz.fazzconnect.gateway.model.Item;
import com.fazz.fazzconnect.gateway.model.Package;
import com.fazz.fazzconnect.gateway.model.Recipient;
import com.fazz.fazzconnect.importer.AutoLinkFileImporter;
import com.fazz.fazzconnect.service.ConsignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.now;

@RestController
public class AnPostController {

    private final AnPostGateway anPostGateway;
    private final AutoLinkFileImporter autoLinkFileImporter;

    public AnPostController(final AnPostGateway anPostGateway,
            final AutoLinkFileImporter autoLinkFileImporter) {
        this.anPostGateway = anPostGateway;
        this.autoLinkFileImporter = autoLinkFileImporter;
    }

    @GetMapping("/carriers")
    public String getCarriers() {
        return anPostGateway.getCarriers();
    }

    @GetMapping("/consignment/{id}")
    public String getConsignment(
            @PathVariable String id
    ) {
        return "Tracking number: " + anPostGateway.getConsignment(id).getTrackingNumber();
    }

    @GetMapping("/import/{workDirectory}/{fileName}")
    public String sendConsignmentsFromAutoLinkFile(
            @PathVariable String workDirectory,
            @PathVariable String fileName) {
        var importedLines = autoLinkFileImporter.importAutoLinkFile(workDirectory, fileName);
        var consignments = ConsignmentService.createConsignments(importedLines);

        return anPostGateway.sendConsignments(consignments);
    }
}
