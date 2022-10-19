package com.fazz.fazzconnect.service;

import com.fazz.fazzconnect.gateway.model.Address;
import com.fazz.fazzconnect.gateway.model.Consignment;
import com.fazz.fazzconnect.gateway.model.Item;
import com.fazz.fazzconnect.gateway.model.Package;
import com.fazz.fazzconnect.gateway.model.Recipient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.now;

public final class ConsignmentService {

    private ConsignmentService() {
    }

    public static List<Consignment> createConsignments (List<String> importedLines) {
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
                    Collections.singletonList(new Item(itemId, itemName, Integer.parseInt(quantity), "",
                            "Ireland", tariff,
                            "", Double.parseDouble(value), Double.parseDouble(weight))
                    )
            );
            var address = new Address(address1, address2, "", city, state, postalCode, country, "");
            var recipient = new Recipient(recipientName, "", "" , companyName, email, phoneNumber, "", "" ,address);
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
                    recipient,
                    ""
            );

            consignments.add(consignment);
        }
        return consignments;
    }

}
