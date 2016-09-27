package com.box.sdk;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import com.eclipsesource.json.JsonArray;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class MetadataTemplateTest {
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    @Category(UnitTest.class)
    public void getMetadataTemplateSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");
        stubFor(get(urlMatching("/metadata_templates/global/properties/schema"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": \"0\"}")));

        MetadataTemplate.getMetadataTemplate(api);
    }

    @Test
    @Category(UnitTest.class)
    public void getMetadataTemplateParseAllFieldsCorrectly() {
        final String templateKey = "productInfo";
        final String scope = "enterprise_12345";
        final String displayName = "Product Info";
        final Boolean isHidden = false;
        final JsonArray fields = JsonArray.readFrom("[\n"
                + "        {\n"
                + "            \"type\": \"float\",\n"
                + "            \"key\": \"skuNumber\",\n"
                + "            \"displayName\": \"SKU Number\",\n"
                + "            \"hidden\": false\n"
                + "        },\n"
                + "        {\n"
                + "            \"type\": \"enum\",\n"
                + "            \"key\": \"department\",\n"
                + "            \"displayName\": \"Department\",\n"
                + "            \"hidden\": false,\n"
                + "            \"options\": [\n"
                + "                {\n"
                + "                    \"key\": \"Beauty\"\n"
                + "                },\n"
                + "                {\n"
                + "                    \"key\": \"Accessories\"\n"
                + "                }\n"
                + "            ]\n"
                + "        }\n"
                + "    ]\n");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");
        stubFor(get(urlMatching("/metadata_templates/global/properties/schema"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n"
                                + "    \"templateKey\": \"productInfo\",\n"
                                + "    \"scope\": \"enterprise_12345\",\n"
                                + "    \"displayName\": \"Product Info\",\n"
                                + "    \"hidden\": false,\n"
                                + "    \"fields\": [\n"
                                + "        {\n"
                                + "            \"type\": \"float\",\n"
                                + "            \"key\": \"skuNumber\",\n"
                                + "            \"displayName\": \"SKU Number\",\n"
                                + "            \"hidden\": false\n"
                                + "        },\n"
                                + "        {\n"
                                + "            \"type\": \"enum\",\n"
                                + "            \"key\": \"department\",\n"
                                + "            \"displayName\": \"Department\",\n"
                                + "            \"hidden\": false,\n"
                                + "            \"options\": [\n"
                                + "                {\n"
                                + "                    \"key\": \"Beauty\"\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"key\": \"Accessories\"\n"
                                + "                }\n"
                                + "            ]\n"
                                + "        }\n"
                                + "    ]\n"
                                + "}")));

        MetadataTemplate template = MetadataTemplate.getMetadataTemplate(api);
        assertEquals(templateKey, template.getTemplateKey());
        assertEquals(scope, template.getScope());
        assertEquals(displayName, template.getDisplayName());
        assertEquals(isHidden, template.isHidden());
        assertEquals(fields, template.getFields());
    }
}
