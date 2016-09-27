package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class MetadataTemplate extends BoxJSONObject{
    private String templateKey;
    private String scope;
    private String displayName;
    private Boolean isHidden;
    private JsonArray fields;

    /**
     * Constructs an empty metadata template.
     */
    public MetadataTemplate() {
        super();
    }

    /**
     * Constructs a metadata template from a JSON string.
     * @param json the json encoded metadate template.
     */
    public MetadataTemplate(String json) {
        super(json);
    }

    MetadataTemplate(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the unique template key to identify the metadata template.
     * @return the unique template key to identify the metadata template.
     */
    public String getTemplateKey() {
        return templateKey;
    }

    /**
     * Gets the metadata template scope.
     * @return the metadata template scope.
     */
    public String getScope() {
        return scope;
    }

    /**
     * Gets the displayed metadata template name.
     * @return the displayed metadata template name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets is the metadata template hidden.
     * @return is the metadata template hidden.
     */
    public Boolean isHidden() {
        return isHidden;
    }

    /**
     * Gets the iterable with all fields the metadata template contains.
     * @return the iterable with all fields the metadata template contains.
     */
    public Iterable<JsonValue> getFields() {
        return fields;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        
    }

}
