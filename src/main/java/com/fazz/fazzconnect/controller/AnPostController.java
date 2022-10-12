package com.fazz.fazzconnect.controller;

import com.fazz.fazzconnect.gateway.AnPostGateway;
import com.fazz.fazzconnect.gateway.model.Address;
import com.fazz.fazzconnect.gateway.model.Consignment;
import com.fazz.fazzconnect.gateway.model.Item;
import com.fazz.fazzconnect.gateway.model.Package;
import com.fazz.fazzconnect.gateway.model.Recipient;
import com.fazz.fazzconnect.importer.AutoLinkFileImporter;
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
@Slf4j
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

    @GetMapping("/consignment")
    public String sendShipmen() {
        return anPostGateway.sendShipment();
    }

    @GetMapping("/import/{workDirectory}/{fileName}")
    public String sendShipmentFromAutoLinkFile(
            @PathVariable String workDirectory,
            @PathVariable String fileName) {
        var importedLines = autoLinkFileImporter.importAutoLinkFile(workDirectory, fileName);
        var consignments = createConsignments(importedLines);

        return anPostGateway.sendShipment(consignments);
    }

    public List<Consignment> createConsignments (List<String> importedLines) {
        List<Consignment> consignments = new ArrayList<>();

        for (var i=0; i<importedLines.size(); i++) {
            var splitLineA = importedLines.get(i).split("\\|");

            var batch = splitLineA[1];
            var orderId = splitLineA[2];
            var recipientName = splitLineA[3];
            var companyName = splitLineA[4];
            var address1 = splitLineA[5];
            var address2 = splitLineA[6];
            var city = splitLineA[7];
            var state = splitLineA[8];
            var postalCode = splitLineA[9];
            var country = splitLineA[10];
            var email = splitLineA[11];
            var phoneNumber = splitLineA[12];
            //var mobileNumber = splitLineA[13];
            //var weight = splitLineA[14];

            i++;

           var splitLineC = importedLines.get(i).split("\\|");

            var itemId = splitLineC[2];
            var itemName = splitLineC[3];
            var quantity = splitLineC[4];
            var weight = splitLineC[5];
            var value = splitLineC[6];
            var tariff = splitLineC[7];
            var countryOrigin = splitLineC[8];

            var consignmentPackage = new Package(
                    itemId, 12.2, 0.5, 10, Double.parseDouble(weight),
                    Collections.singletonList(new Item(itemId, itemName, Integer.parseInt(quantity), tariff,
                            "Ireland", "",
                            "", Double.parseDouble(value), Double.parseDouble(weight))
                    )
            );
            var address = new Address(address1, address2, "", city, state, postalCode, country, "");
            var recipient = new Recipient(recipientName, "", "" , companyName, email, "", "", "" ,address);
            var consignment = new Consignment (
                    batch,
                    orderId,
                    now().toString(),
                    "An Post",
                    "An Post|Express International Packet",
                    recipientName,
                    address1,
                    address2,
                    city,
                    state,
                    postalCode,
                    country,
                    email,
                    phoneNumber,
                    weight,
                    Collections.singletonList(consignmentPackage),
                    recipient
            );

            log.info("Order id: {}", orderId);
            log.info("Carrier: {}", consignment.getCarrier());
            log.info("Service ID: {}", consignment.getServiceId());
            consignments.add(consignment);
        }
        return consignments;
    }
}
