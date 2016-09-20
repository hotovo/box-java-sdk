package com.box.sdk;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxCollection} related unit tests.
 */
public class BoxCollectionTest {

    /**
     * Unit test for {@link BoxCollection#getAllCollections(BoxAPIConnection)}.
     */
    @Test
    @Category(UnitTest.class)
    public void getCollectionsParsesAllFieldsCorrectly() {
        final String id = "405151";
        final String name = "Favorites";
        final String collectionType = "favorites";

        JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"total_count\": 1,\n"
                + "    \"entries\": [\n"
                + "        {\n"
                + "            \"type\": \"collection\",\n"
                + "            \"id\": \"405151\",\n"
                + "            \"name\": \"Favorites\",\n"
                + "            \"collection_type\": \"favorites\"\n"
                + "        }\n"
                + "    ],\n"
                + "    \"limit\": 100,\n"
                + "    \"offset\": 0\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxCollection.Info> iterator = BoxCollection.getAllCollections(api).iterator();
        BoxCollection.Info collection = iterator.next();
        assertEquals(id, collection.getID());
        assertEquals(name, collection.getName());
        assertEquals(collectionType, collection.getCollectionType());
        assertEquals(false, iterator.hasNext());
    }

}
