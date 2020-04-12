package org.ahenteti.cli.util;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import java.nio.file.Path;

/**
 * project custom VelocityEngine wrapper
 * <p>
 * code inspiration
 * <p>
 * https://stackoverflow.com/questions/19443366/how-to-disable-velocity-logs
 * https://stackoverflow.com/questions/3229197/where-does-velocity-search-for-the-template
 */
public class ProjectVelocityEngine extends VelocityEngine {

    public ProjectVelocityEngine(Path resourceLoaderPath) {
        super();
        addProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogChute");
        addProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, resourceLoaderPath.toString());
    }

}
