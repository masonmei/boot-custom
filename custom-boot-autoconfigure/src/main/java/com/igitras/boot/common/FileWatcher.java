package com.igitras.boot.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

/**
 * Created by mason on 10/29/15.
 */
public class FileWatcher<T> {
    private static final Logger LOG = LoggerFactory.getLogger(FileWatcher.class);

    private final int refreshIntervalInSecond;
    private final File configFile;

    private final Class<T> contentType;
    private T ipListHolder;

    public FileWatcher(File configFile, Class<T> type) {
        this(1, configFile, type);
    }

    public FileWatcher(int refreshIntervalInSecond, File configFile, Class<T> type) {
        Assert.state(refreshIntervalInSecond > 0, "The minimum refresh interval is 1 second");
        Assert.notNull(configFile, "Configuration File must not be null.");
        Assert.notNull(type, "Content type must null be null.");

        if (!configFile.exists()) {
            LOG.error("Configuration File not exist.");
            throw new IllegalArgumentException("Configuration File not exists.");
        }

        if (configFile.isDirectory()) {
            LOG.error("Configuration File required a file, given a directory. Given path is {}", configFile.getPath());
            throw new IllegalArgumentException("Configuration File should not be a directory.");
        }

        if (!configFile.canRead()) {
            LOG.error("No permission to read Configuration File, given path is {}", configFile.getPath());
            throw new IllegalArgumentException("Configuration File cannot be read.");
        }

        this.refreshIntervalInSecond = refreshIntervalInSecond;
        this.configFile = configFile;
        this.contentType = type;

        loadingProperties();
        watchingForChanges();
    }

    public T getIpListHolder() {
        return ipListHolder;
    }

    private void watchingForChanges() {
        new Thread(() -> {
            try {
                final Path parentPath = Paths.get(configFile.getParent());
                final WatchService watchService = FileSystems.getDefault().newWatchService();
                parentPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

                while (true) {
                    final WatchKey watchKey = watchService.poll(refreshIntervalInSecond, TimeUnit.SECONDS);
                    if (watchKey != null) {
                        watchKey.pollEvents().stream()
                                .forEach(event -> {
                                    if (configFile.getName().equals(event.context().toString())) {
                                        LOG.debug("Start to loading properties changes.");
                                        loadingProperties();
                                        LOG.debug("Finish to load properties changes.");
                                    }
                                });
                    }
                }

            } catch (IOException | InterruptedException e) {
                LOG.warn("Watching file failed or reloading failed");
            }
        }).start();
    }

    private void loadingProperties() {
        LOG.debug("start to load properties with path {}", configFile.getName());

        try (InputStream inputStream = new FileInputStream(configFile)) {
            Yaml yaml = new Yaml();
            ipListHolder = yaml.loadAs(inputStream, contentType);
        } catch (IOException e) {
            LOG.warn("Cannot reading configurations");
        }

        LOG.info("reload properties finished.");
    }

}
