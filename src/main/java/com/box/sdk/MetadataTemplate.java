package com.box.sdk;

import java.net.URL;

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
    private static final URLTemplate METADATA_TEMPLATE_URL_TEMPLATE
            = new URLTemplate("metadata_templates/%s/%s/schema");
    private static final String DEFAULT_METADATA_TYPE = "properties";
    private static final String GLOBAL_METADATA_SCOPE = "global";
    private static final String ENTERPRISE_METADATA_SCOPE = "enterprise";

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

    /**
     * Gets the metadata template of properties.
     * @param api the API connection to be used.
     * @return the metadata template returned from the server.
     */
    public static MetadataTemplate getMetadataTemplate(BoxAPIConnection api) {
        return getMetadataTemplate(api, DEFAULT_METADATA_TYPE);
    }

    /**
     * Gets the metadata template of specified template type.
     * @param api the API connection to be used.
     * @param templateName the metadata template type name.
     * @return the metadata template returned from the server.
     */
    public static MetadataTemplate getMetadataTemplate(BoxAPIConnection api, String templateName) {
        String scope = scopeBasedOnType(templateName);
        return getMetadataTemplate(api, templateName, scope);
    }

    /**
     * Gets the metadata template of specified template type.
     * @param api the API connection to be used.
     * @param templateName the metadata template type name.
     * @param scope the metadata template scope (global or enterprise).
     * @return the metadata template returned from the server.
     */
    public static MetadataTemplate getMetadataTemplate(BoxAPIConnection api, String templateName, String scope) {
        URL url = METADATA_TEMPLATE_URL_TEMPLATE.build(api.getBaseURL(), scope, templateName);
        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new MetadataTemplate(response.getJSON());
    }

    private static String scopeBasedOnType(String typeName) {
        String scope;
        if (typeName.equals(DEFAULT_METADATA_TYPE)) {
            scope = GLOBAL_METADATA_SCOPE;
        } else {
            scope = ENTERPRISE_METADATA_SCOPE;
        }
        return scope;
    }

}
