package com.fazz.fazzconnect.controler;

import com.fazz.fazzconnect.controller.AnPostController;
import com.fazz.fazzconnect.gateway.AnPostGateway;
import com.fazz.fazzconnect.importer.AutoLinkFileImporter;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.openMocks;

public class AnPostControllerTests {

    private final AnPostController anPostController;

    @Mock
    private AutoLinkFileImporter autoLinkFileImporter;

    @Mock
    private AnPostGateway anPostGateway;

    public AnPostControllerTests() {
        openMocks(this);
        anPostController = new AnPostController(anPostGateway, autoLinkFileImporter);
    }

//    @Test
//    public void shouldCallSendShipmentFromAutoLinkFileSuccessfully() {
//        var response = anPostController
//                .sendShipmentFromAutoLinkFile("workDirectory", "fileName");
//
//        assertThat(response).isEqualTo("");
//    }
}
