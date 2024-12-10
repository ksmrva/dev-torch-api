package io.ksmrva.visual.torch.api.arg.constant;

// @formatter:off
public abstract class DevTorchApiConstants {

    /**
     * API-wide URI Path values
     */
    public final static String API_VERSION = "1";
    public final static String BASE_URI_PATH = "/api/v" + API_VERSION;

    /**
     * Model URI Path values
     */
    public final static String MODEL_BASE_URI_PATH = BASE_URI_PATH + "/model";

    /**
     * Database URI Path values
     */
    public final static String DATABASE_MODEL_BASE_URI_PATH = MODEL_BASE_URI_PATH + "/db";
    public final static String DATABASE_MODEL_SOURCE_BASE_URI_PATH = DATABASE_MODEL_BASE_URI_PATH + "/source";
    public final static String DATABASE_MODEL_COMPONENT_BASE_URI_PATH = DATABASE_MODEL_BASE_URI_PATH + "/component";

    /**
     * Code URI Path values
     */
    public final static String CODE_MODEL_BASE_URI_PATH = MODEL_BASE_URI_PATH + "/code";
    public final static String CODE_MODEL_SOURCE_BASE_URI_PATH = CODE_MODEL_BASE_URI_PATH + "/source";
    public final static String CODE_MODEL_SOURCE_FILE_BASE_URI_PATH = CODE_MODEL_SOURCE_BASE_URI_PATH + "/file";
    public final static String CODE_MODEL_SOURCE_PROJECT_BASE_URI_PATH = CODE_MODEL_SOURCE_BASE_URI_PATH + "/project";
    public final static String CODE_MODEL_COMPONENT_BASE_URI_PATH = CODE_MODEL_BASE_URI_PATH + "/component";

    /**
     * Documentation URI Path values
     */
    public final static String DOCUMENTATION_BASE_URI_PATH = BASE_URI_PATH + "/documentation";
    public final static String DOCUMENTATION_TOOL_BASE_URI_PATH = DOCUMENTATION_BASE_URI_PATH + "/tool";

    /**
     * Canvas URI Path values
     */
    public final static String DOCUMENTATION_TOOL_CANVAS_BASE_URI_PATH = DOCUMENTATION_TOOL_BASE_URI_PATH + "/canvas";

}
// @formatter:on