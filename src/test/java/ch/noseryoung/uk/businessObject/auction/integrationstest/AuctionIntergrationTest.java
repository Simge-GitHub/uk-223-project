package ch.noseryoung.uk.businessObject.auction.integrationstest;

import ch.noseryoung.uk.businessObject.auction.Auction;
import ch.noseryoung.uk.businessObject.auction.AuctionRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//H2 wird gecleart
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuctionIntergrationTest {

    @Autowired
    //Anfrage an restAPI
    private MockMvc mockMvc;

    @Autowired
    private AuctionRepository auctionRepository;

    @Test
    public void findAll_requestAllAuctions_returnsAllAuctions() throws Exception {
        Auction auction1 = new Auction().setName("ABC").setPrice(300);
        Auction auction2 = new Auction().setName("XYZ").setPrice(333);
        auctionRepository.save(auction1);
        auctionRepository.save(auction2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/auctions/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.[*].id", Matchers.containsInAnyOrder(auction1.getId(), auction2.getId(), 5)));
    }
}