package com.box.sdk;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

/**
 * {@link MetadataTemplate} related unit tests.
 */
public class MetadataTemplateTest {

    /**
     * Wiremock
     */
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(8080);

    /**
     * Unit test for {@link MetadataTemplate#getMetadataTemplate(BoxAPIConnection)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetMetadataTemplateSendsCorrectRequest() {
       // WireMock.stubFor(WireMock.get(WireMock.urlMatching("/metadata_templates/global/properties/schema"))
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/metadata_templates/global/properties/schema"
                                + "?fields=displayName%2Chidden",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        MetadataTemplate.getMetadataTemplate(api, "properties", "global", "displayName", "hidden");
    }

    /**
     * Unit test for {@link MetadataTemplate#getMetadataTemplate(BoxAPIConnection)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetMetadataTemplateParseAllFieldsCorrectly() {
        final String templateKey = "productInfo";
        final String scope = "enterprise_12345";
        final String displayName = "Product Info";
        final Boolean isHidden = false;
        final String firstFieldType = "float";
        final String firstFieldKey = "skuNumber";
        final String firstFieldDisplayName = "SKU Number";
        final Boolean firstFieldIsHidden = false;
        final String secondFieldType = "enum";
        final String secondFieldKey = "department";
        final String secondFieldDisplayName = "Department";
        final Boolean secondFieldIsHidden = false;
        final String secondFieldFirstOption = "Beauty";
        final String secondFieldSecondOption = "Accessories";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");
        WireMock.stubFor(WireMock.get(WireMock.urlMatching("/metadata_templates/global/properties/schema"))
                .willReturn(WireMock.aResponse()
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
        Assert.assertEquals(templateKey, template.getTemplateKey());
        Assert.assertEquals(scope, template.getScope());
        Assert.assertEquals(displayName, template.getDisplayName());
        Assert.assertEquals(isHidden, template.getIsHidden());
        List<MetadataTemplate.Field> templateFields = template.getFields();
        Assert.assertEquals(firstFieldType, templateFields.get(0).getType());
        Assert.assertEquals(firstFieldKey, templateFields.get(0).getKey());
        Assert.assertEquals(firstFieldDisplayName, templateFields.get(0).getDisplayName());
        Assert.assertEquals(firstFieldIsHidden, templateFields.get(0).getIsHidden());
        Assert.assertEquals(secondFieldType, templateFields.get(1).getType());
        Assert.assertEquals(secondFieldKey, templateFields.get(1).getKey());
        Assert.assertEquals(secondFieldDisplayName, templateFields.get(1).getDisplayName());
        Assert.assertEquals(secondFieldIsHidden, templateFields.get(1).getIsHidden());
        Assert.assertEquals(secondFieldFirstOption, templateFields.get(1).getOptions().get(0));
        Assert.assertEquals(secondFieldSecondOption, templateFields.get(1).getOptions().get(1));

    }
}
