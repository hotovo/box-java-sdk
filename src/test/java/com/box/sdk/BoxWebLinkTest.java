package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

public class BoxWebLinkTest {

    @Test
    @Category(UnitTest.class)
    public void createWebLinkSendsCorrectJsonWithNameAndDescription() {
        final String url = "http://";
        final String parentFolderID = "0";
        final String name = "non-empty name";
        final String description = "non-empty description";

        final JsonObject fakeJSONResponse = new JsonObject()
                .add("type", "web_link")
                .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(url, json.get("url").asString());
                assertEquals(parentFolderID, json.get("parent").asObject().get("id").asString());
                assertEquals(name, json.get("name").asString());
                assertEquals(description, json.get("description").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxWebLink.create(api, url, parentFolderID, name, description);
    }

    @Test
    @Category(UnitTest.class)
    public void createWebLinkSendsCorrectJsonWithoutNameAndDescription() {
        final String url = "http://";
        final String parentFolderID = "0";
        final String description = "";

        final JsonObject fakeJSONResponse = new JsonObject()
                .add("type", "web_link")
                .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(url, json.get("url").asString());
                assertEquals(parentFolderID, json.get("parent").asObject().get("id").asString());
                assertEquals(url, json.get("name").asString());
                assertEquals(description, json.get("description").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxWebLink.create(api, url, parentFolderID);
    }

    @Test
    @Category(UnitTest.class)
    public void copyWebLinkSendsCorrectJson() {
        final String parentFolderID = "0";
        final String name = "non-empty name";

        final JsonObject fakeJSONResponse = new JsonObject()
                .add("type", "web_link")
                .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(parentFolderID, json.get("parent").asObject().get("id").asString());
                assertEquals(name, json.get("name").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxWebLink weblink = new BoxWebLink(api, "0");
        weblink.copy(new BoxFolder(api, "0"), name);
    }

    @Test
    @Category(UnitTest.class)
    public void moveWebLinkSendsCorrectJson() {
        final String parentFolderID = "0";
        final String name = "non-empty name";

        final JsonObject fakeJSONResponse = new JsonObject()
                .add("type", "web_link")
                .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(parentFolderID, json.get("parent").asObject().get("id").asString());
                assertEquals(name, json.get("name").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxWebLink weblink = new BoxWebLink(api, "0");
        weblink.move(new BoxFolder(api, "0"), name);
    }

    @Test
    @Category(UnitTest.class)
    public void renameWebLinkSendsCorrectJson() {
        final String name = "non-empty name";

        final JsonObject fakeJSONResponse = new JsonObject()
                .add("type", "web_link")
                .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(name, json.get("name").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxWebLink weblink = new BoxWebLink(api, "0");
        weblink.rename(name);
    }

    @Test
    @Category(UnitTest.class)
    public void updateInfoSendsCorrectJson() {
        final String name = "non-empty name";

        final JsonObject fakeJSONResponse = new JsonObject()
                .add("type", "web_link")
                .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(name, json.get("name").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxWebLink weblink = new BoxWebLink(api, "0");
        BoxWebLink.Info info = weblink.new Info();
        info.addPendingChange("name", name);
        weblink.updateInfo(info);
    }

    @Test
    @Category(UnitTest.class)
    public void getInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "6742981";
        final String sequenceID = "0";
        final String etag = "0";
        final String name = "Box Website";
        final String url = "https://www.box.com";
        final String creatorID = "10523870";
        final String creatorName = "Ted Blosser";
        final String creatorLogin = "ted+demo@box.com";
        final Date createdAt = BoxDateFormat.parse("2015-05-07T14:31:16-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2015-05-07T14:31:16-07:00");
        final String parentID = "848123342";
        final String parentSequenceID = "1";
        final String parentEtag = "1";
        final String parentName = "Documentation";
        final String description = "Cloud Content Management";
        final String itemStatus = "active";
        final Date trashedAt = null;
        final Date purgedAt = null;
        final BoxSharedLink sharedLink = null;
        final String pathID = "848123342";
        final String pathSequenceID = "1";
        final String pathEtag = "1";
        final String pathName = "Documentation";
        final String modifiedID = "10523870";
        final String modifiedName = "Ted Blosser";
        final String modifiedLogin = "ted+demo@box.com";
        final String ownerID = "10523870";
        final String ownerName = "Ted Blosser";
        final String ownerLogin = "ted+demo@box.com";


        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"web_link\",\n"
                + "    \"id\": \"6742981\",\n"
                + "    \"sequence_id\": \"0\",\n"
                + "    \"etag\": \"0\",\n"
                + "    \"name\": \"Box Website\",\n"
                + "    \"url\": \"https://www.box.com\",\n"
                + "    \"created_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"10523870\",\n"
                + "        \"name\": \"Ted Blosser\",\n"
                + "        \"login\": \"ted+demo@box.com\"\n"
                + "    },\n"
                + "    \"created_at\": \"2015-05-07T14:31:16-07:00\",\n"
                + "    \"modified_at\": \"2015-05-07T14:31:16-07:00\",\n"
                + "    \"parent\": {\n"
                + "        \"type\": \"folder\",\n"
                + "        \"id\": \"848123342\",\n"
                + "        \"sequence_id\": \"1\",\n"
                + "        \"etag\": \"1\",\n"
                + "        \"name\": \"Documentation\"\n"
                + "    },\n"
                + "    \"description\": \"Cloud Content Management\",\n"
                + "    \"item_status\": \"active\",\n"
                + "    \"trashed_at\": null,\n"
                + "    \"purged_at\": null,\n"
                + "    \"shared_link\": null,\n"
                + "    \"path_collection\": {\n"
                + "        \"total_count\": 1,\n"
                + "        \"entries\": [\n"
                + "            {\n"
                + "                \"type\": \"folder\",\n"
                + "                \"id\": \"848123342\",\n"
                + "                \"sequence_id\": \"1\",\n"
                + "                \"etag\": \"1\",\n"
                + "                \"name\": \"Documentation\"\n"
                + "            }\n"
                + "        ]\n"
                + "    },\n"
                + "    \"modified_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"10523870\",\n"
                + "        \"name\": \"Ted Blosser\",\n"
                + "        \"login\": \"ted+demo@box.com\"\n"
                + "    },\n"
                + "    \"owned_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"10523870\",\n"
                + "        \"name\": \"Ted Blosser\",\n"
                + "        \"login\": \"ted+demo@box.com\"\n"
                + "    }\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxWebLink.Info info = new BoxWebLink(api, "6742981").getInfo();
        assertEquals(id, info.getID());
        assertEquals(sequenceID, info.getSequenceID());
        assertEquals(etag, info.getEtag());
        assertEquals(name, info.getName());
        assertEquals(url, info.getLinkURL().toString());
        assertEquals(createdAt, info.getCreatedAt());
        assertEquals(modifiedAt, info.getModifiedAt());
        assertEquals(description, info.getDescription());
        assertEquals(itemStatus, info.getItemStatus());
        assertEquals(trashedAt, info.getTrashedAt());
        assertEquals(purgedAt, info.getPurgedAt());
        assertEquals(sharedLink, info.getSharedLink());
        BoxUser.Info creatorInfo = info.getCreatedBy();
        assertEquals(creatorID, creatorInfo.getID());
        assertEquals(creatorName, creatorInfo.getName());
        assertEquals(creatorLogin, creatorInfo.getLogin());
        BoxUser.Info modifiedInfo = info.getModifiedBy();
        assertEquals(modifiedID, modifiedInfo.getID());
        assertEquals(modifiedName, modifiedInfo.getName());
        assertEquals(modifiedLogin, modifiedInfo.getLogin());
        BoxUser.Info ownerInfo = info.getOwnedBy();
        assertEquals(ownerID, ownerInfo.getID());
        assertEquals(ownerName, ownerInfo.getName());
        assertEquals(ownerLogin, ownerInfo.getLogin());
        BoxFolder.Info parentInfo = info.getParent();
        assertEquals(parentID, parentInfo.getID());
        assertEquals(parentSequenceID, parentInfo.getSequenceID());
        assertEquals(parentEtag, parentInfo.getEtag());
        assertEquals(parentName, parentInfo.getName());
        BoxFolder.Info pathInfo = info.getPathCollection().get(0);
        assertEquals(pathID, pathInfo.getID());
        assertEquals(pathSequenceID, pathInfo.getSequenceID());
        assertEquals(pathEtag, pathInfo.getEtag());
        assertEquals(pathName, pathInfo.getName());
    }

    @Test
    @Category(IntegrationTest.class)
    public void copyWebLinkSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String originalFileName = "[copyWebLinkSucceeds] Original WebLink";
        String newFileName = "[copyWebLinkSucceeds] New WebLink";
        URL url = new URL("https://api.box.com");
        String description = "[copyWebLinkSucceeds] Original WebLink";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(originalFileName, url, description).getResource();
        BoxWebLink.Info uploadedWebLinkInfo = uploadedWebLink.getInfo();

        BoxWebLink.Info copiedWebLinkInfo = uploadedWebLink.copy(rootFolder, newFileName);
        BoxWebLink copiedWebLink = copiedWebLinkInfo.getResource();

        assertThat(copiedWebLinkInfo.getLinkURL(), equalTo(uploadedWebLinkInfo.getLinkURL()));
        assertThat(copiedWebLinkInfo.getDescription(), equalTo(uploadedWebLinkInfo.getDescription()));

        uploadedWebLink.delete();
        copiedWebLink.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void moveWebLinkSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[moveWebLinkSucceeds] Test WebLink";
        URL url = new URL("https://api.box.com");
        String description = "[moveWebLinkSucceeds] Test WebLink";
        String folderName = "[moveWebLinkSucceeds] Destination Folder";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(fileName, url, description).getResource();

        BoxFolder destinationFolder = rootFolder.createFolder(folderName).getResource();
        uploadedWebLink.move(destinationFolder);

        assertThat(destinationFolder,
            hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(uploadedWebLink.getID()))));

        uploadedWebLink.delete();
        destinationFolder.delete(false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithOnlyTheURLField() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getInfoWithOnlyTheURLField] Test WebLink";
        URL url = new URL("https://api.box.com");
        String description = "[getInfoWithOnlyTheURLField] Test WebLink";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(fileName, url, description).getResource();
        BoxWebLink.Info uploadedLinkInfo = uploadedWebLink.getInfo("url");

        assertThat(uploadedLinkInfo.getLinkURL(), is(equalTo(url)));
        assertThat(uploadedLinkInfo.getDescription(), is(nullValue()));

        uploadedLinkInfo.getResource().delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void getInfoWithAllFields() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String fileName = "[getInfoWithAllFields] Test WebLink";
        URL url = new URL("https://api.box.com");
        String description = "[getInfoWithAllFields] Test WebLink";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(fileName, url, description).getResource();
        BoxWebLink.Info uploadedLinkInfo = uploadedWebLink.getInfo(BoxWebLink.ALL_FIELDS);

        assertThat(uploadedLinkInfo.getName(), is(equalTo(fileName)));
        assertThat(uploadedLinkInfo.getLinkURL(), is(equalTo(url)));
        assertThat(uploadedLinkInfo.getDescription(), is(equalTo(description)));

        uploadedLinkInfo.getResource().delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateLinkWithSpecialCharsInNameSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String originalFileName = "[updateLinkWithSpecialCharsInNameSucceeds] abc\";def";
        URL url = new URL("https://api.box.com");
        String description = "[updateLinkWithSpecialCharsInNameSucceeds] Test WebLink";

        BoxWebLink.Info uploadedWebLinkInfo = rootFolder.createWebLink(originalFileName, url, description);

        assertThat(uploadedWebLinkInfo.getName(), is(equalTo(originalFileName)));

        uploadedWebLinkInfo.getResource().delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateLinkInfoSucceeds() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        String originalFileName = "[updateLinkInfoSucceeds] Original Name";
        String newFileName = "[updateLinkInfoSucceeds] New Name";
        URL url = new URL("https://api.box.com");
        String description = "[updateLinkWithSpecialCharsInNameSucceeds] Test WebLink";

        BoxWebLink uploadedWebLink = rootFolder.createWebLink(originalFileName, url, description).getResource();

        BoxWebLink.Info newInfo = uploadedWebLink.new Info();
        newInfo.setName(newFileName);
        uploadedWebLink.updateInfo(newInfo);

        assertThat(newInfo.getName(), is(equalTo(newFileName)));

        uploadedWebLink.delete();
    }
}
