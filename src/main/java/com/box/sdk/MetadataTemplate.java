package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * The MetadataTemplate class represents one type instance of Box metadata template.
 *
 * Learn more about Box metadata templates:
 * https://docs.box.com/reference#metadata-templates
 */
public class MetadataTemplate extends BoxJSONObject {
    /**
     * @see #getTemplateKey()
     */
    private String templateKey;

    /**
     * @see #getScope()
     */
    private String scope;

    /**
     * @see #getDisplayName()
     */
    private String displayName;

    /**
     * @see #isHidden()
     */
    private Boolean isHidden;

    /**
     * @see #getFields()
     */
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
        return this.templateKey;
    }

    /**
     * Gets the metadata template scope.
     * @return the metadata template scope.
     */
    public String getScope() {
        return this.scope;
    }

    /**
     * Gets the displayed metadata template name.
     * @return the displayed metadata template name.
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Gets is the metadata template hidden.
     * @return is the metadata template hidden.
     */
    public Boolean isHidden() {
        return this.isHidden;
    }

    /**
     * Gets the iterable with all fields the metadata template contains.
     * @return the iterable with all fields the metadata template contains.
     */
    public Iterable<JsonValue> getFields() {
        return this.fields;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        if (memberName.equals("templateKey")) {
            this.templateKey = value.asString();
        } else if (memberName.equals("scope")) {
            this.scope = value.asString();
        } else if (memberName.equals("displayName")) {
            this.displayName = value.asString();
        } else if (memberName.equals("hidden")) {
            this.isHidden = value.asBoolean();
        } else if (memberName.equals("fields")) {
            this.fields = value.asArray();
        }
    }

}
