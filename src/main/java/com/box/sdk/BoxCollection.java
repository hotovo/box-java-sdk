package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Collections contain information about the items contained inside of them, including files and folders.
 * The only collection available currently is a “Favorites” collection.
 * <p>
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("collection")
public class BoxCollection extends BoxResource {
    private static final URLTemplate GET_COLLECTIONS_URL_TEMPLATE = new URLTemplate("collections/");

    /**
     * Constructs a BoxCollection for a collection with a given ID.
     *
     * @param api the API connection to be used by the collection.
     * @param id  the ID of the collection.
     */
    public BoxCollection(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets an iterable of all the collections for the given user.
     * @param  api the API connection to be used when retrieving the collections.
     * @return     an iterable containing info about all the collections.
     */
    public static Iterable<BoxCollection.Info> getAllCollections(final BoxAPIConnection api) {
        return new Iterable<BoxCollection.Info>() {
            public Iterator<BoxCollection.Info> iterator() {
                URL url = GET_COLLECTIONS_URL_TEMPLATE.build(api.getBaseURL());
                return new BoxCollectionIterator(api, url);
            }
        };
    }

    /**
     * Contains information about a BoxCollection.
     */
    public class Info extends BoxResource.Info {
        /**
         * @see #getCollectionType()
         */
        private String collectionType;

        /**
         * @see #getName()
         */
        private String name;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         *
         * @param json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         *
         * @param jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the type of the collection.
         * Currently only "favorites" collection type is supported.
         *
         * @return the type of the collection.
         */
        public String getCollectionType() {
            return this.collectionType;
        }

        /**
         * Gets the name of the collection.
         * The only collection currently available is named “Favorites”.
         *
         * @return the name of the collection.
         */
        public String getName() {
            return this.name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxResource getResource() {
            return BoxCollection.this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            if (memberName.equals("collection_type")) {
                this.collectionType = value.asString();
            } else if (memberName.equals("name")) {
                this.name = value.asString();
            }
        }
    }
}
