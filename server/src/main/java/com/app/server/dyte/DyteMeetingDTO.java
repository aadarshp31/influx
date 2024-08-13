package com.app.server.dyte;

import java.time.ZonedDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import static com.app.server.dyte.Constants.*;

public class DyteMeetingDTO {

    @JsonProperty(value = TITLE)
    private String title;

    @JsonProperty(value = PREFERRED_REGION)
    private String preferredRegion;

    @JsonProperty(value = RECORD_ON_START)
    private Boolean recordOnStart;

    @JsonProperty(value = LIVE_STREAM_ON_START)
    private Boolean liveStreamOnStart;

    @JsonProperty(value = RECORDING_CONFIG)
    private RecordingConfig recordingConfig;

    @JsonProperty(value = PERSIST_CHAT)
    private Boolean persistChat;

    @JsonProperty(value = SUMMARIZE_ON_END)
    private Boolean summarizeOnEnd;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreferredRegion() {
        return preferredRegion;
    }

    public void setPreferredRegion(String preferredRegion) {
        this.preferredRegion = preferredRegion;
    }

    public Boolean getRecordOnStart() {
        return recordOnStart;
    }

    public void setRecordOnStart(Boolean recordOnStart) {
        this.recordOnStart = recordOnStart;
    }

    public Boolean getLiveStreamOnStart() {
        return liveStreamOnStart;
    }

    public void setLiveStreamOnStart(Boolean liveStreamOnStart) {
        this.liveStreamOnStart = liveStreamOnStart;
    }

    public RecordingConfig getRecordingConfig() {
        return recordingConfig;
    }

    public void setRecordingConfig(RecordingConfig recordingConfig) {
        this.recordingConfig = recordingConfig;
    }

    public Boolean getPersistChat() {
        return persistChat;
    }

    public void setPersistChat(Boolean persistChat) {
        this.persistChat = persistChat;
    }

    public Boolean getSummarizeOnEnd() {
        return summarizeOnEnd;
    }

    public void setSummarizeOnEnd(Boolean summarizeOnEnd) {
        this.summarizeOnEnd = summarizeOnEnd;
    }

    @Override
    public String toString() {
        return "DyteMeetingDTO{" +
                "title='" + title + '\'' +
                ", preferredRegion='" + preferredRegion + '\'' +
                ", recordOnStart=" + recordOnStart +
                ", liveStreamOnStart=" + liveStreamOnStart +
                ", recordingConfig=" + recordingConfig +
                ", persistChat=" + persistChat +
                ", summarizeOnEnd=" + summarizeOnEnd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DyteMeetingDTO that = (DyteMeetingDTO) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(preferredRegion, that.preferredRegion) &&
                Objects.equals(recordOnStart, that.recordOnStart) &&
                Objects.equals(liveStreamOnStart, that.liveStreamOnStart) &&
                Objects.equals(recordingConfig, that.recordingConfig) &&
                Objects.equals(persistChat, that.persistChat) &&
                Objects.equals(summarizeOnEnd, that.summarizeOnEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, preferredRegion, recordOnStart, liveStreamOnStart, recordingConfig, persistChat, summarizeOnEnd);
    }

    // Nested static classes with getters and setters
    public static class RecordingConfig {
        
        @JsonProperty(value = MAX_SECONDS)
        private Integer maxSeconds;

        @JsonProperty(value = FILE_NAME_PREFIX)
        private String fileNamePrefix;
        
        @JsonProperty(value = VIDEO_CONFIG)
        private VideoConfig videoConfig;
        
        @JsonProperty(value = AUDIO_CONFIG)
        private AudioConfig audioConfig;

        @JsonProperty(value = STORAGE_CONFIG)
        private StorageConfig storageConfig;

        @JsonProperty(value = DYTE_BUCKET_CONFIG)
        private DyteBucketConfig dyteBucketConfig;

        @JsonProperty(value = LIVE_STREAMING_CONFIG)
        private LiveStreamingConfig liveStreamingConfig;

        // Getters and Setters
        public Integer getMaxSeconds() {
            return maxSeconds;
        }

        public void setMaxSeconds(Integer maxSeconds) {
            this.maxSeconds = maxSeconds;
        }

        public String getFileNamePrefix() {
            return fileNamePrefix;
        }

        public void setFileNamePrefix(String fileNamePrefix) {
            this.fileNamePrefix = fileNamePrefix;
        }

        public VideoConfig getVideoConfig() {
            return videoConfig;
        }

        public void setVideoConfig(VideoConfig videoConfig) {
            this.videoConfig = videoConfig;
        }

        public AudioConfig getAudioConfig() {
            return audioConfig;
        }

        public void setAudioConfig(AudioConfig audioConfig) {
            this.audioConfig = audioConfig;
        }

        public StorageConfig getStorageConfig() {
            return storageConfig;
        }

        public void setStorageConfig(StorageConfig storageConfig) {
            this.storageConfig = storageConfig;
        }

        public DyteBucketConfig getDyteBucketConfig() {
            return dyteBucketConfig;
        }

        public void setDyteBucketConfig(DyteBucketConfig dyteBucketConfig) {
            this.dyteBucketConfig = dyteBucketConfig;
        }

        public LiveStreamingConfig getLiveStreamingConfig() {
            return liveStreamingConfig;
        }

        public void setLiveStreamingConfig(LiveStreamingConfig liveStreamingConfig) {
            this.liveStreamingConfig = liveStreamingConfig;
        }
    }

    public static class VideoConfig {

        @JsonProperty(value = VIDEO_CODEC)
        private String codec;

        @JsonProperty(value = VIDEO_WIDTH)
        private Integer width;

        @JsonProperty(value = VIDEO_HEIGHT)
        private Integer height;

        @JsonProperty(value = WATERMARK)
        private Watermark watermark;

        @JsonProperty(value = VIDEO_EXPORT_FILE)
        private Boolean exportFile;

        // Getters and Setters
        public String getCodec() {
            return codec;
        }

        public void setCodec(String codec) {
            this.codec = codec;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Watermark getWatermark() {
            return watermark;
        }

        public void setWatermark(Watermark watermark) {
            this.watermark = watermark;
        }

        public Boolean getExportFile() {
            return exportFile;
        }

        public void setExportFile(Boolean exportFile) {
            this.exportFile = exportFile;
        }

        public static class Watermark {
            @JsonProperty(value = WATERMARK_URL)
            private String url;

            @JsonProperty(value = WATERMARK_SIZE)
            private Size size;

            @JsonProperty(value = WATERMARK_POSITION)
            private String position;

            // Getters and Setters
            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Size getSize() {
                return size;
            }

            public void setSize(Size size) {
                this.size = size;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }
            
            public static class Size {
                @JsonProperty(value = WATERMARK_SIZE_WIDTH)
                private Integer width;
                @JsonProperty(value = WATERMARK_SIZE_HEIGHT)
                private Integer height;

                // Getters and Setters
                public Integer getWidth() {
                    return width;
                }

                public void setWidth(Integer width) {
                    this.width = width;
                }

                public Integer getHeight() {
                    return height;
                }

                public void setHeight(Integer height) {
                    this.height = height;
                }
            }
        }
    }

    public static class AudioConfig {
        @JsonProperty(value = AUDIO_CODEC)
        private String codec;
        @JsonProperty(value = AUDIO_CHANNEL)
        private String channel;
        @JsonProperty(value = AUDIO_EXPORT_FILE)
        private Boolean exportFile;

        // Getters and Setters
        public String getCodec() {
            return codec;
        }

        public void setCodec(String codec) {
            this.codec = codec;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public Boolean getExportFile() {
            return exportFile;
        }

        public void setExportFile(Boolean exportFile) {
            this.exportFile = exportFile;
        }
    }

    public static class StorageConfig {
        @JsonProperty(value = STORAGE_TYPE)
        private String type;

        @JsonProperty(value = STORAGE_ACCESS_KEY)
        private String accessKey;

        @JsonProperty(value = STORAGE_SECRET)
        private String secret;

        @JsonProperty(value = STORAGE_BUCKET)
        private String bucket;

        @JsonProperty(value = STORAGE_REGION)
        private String region;

        @JsonProperty(value = STORAGE_PATH)
        private String path;

        @JsonProperty(value = STORAGE_AUTH_METHOD)
        private String authMethod;

        @JsonProperty(value = STORAGE_USERNAME)
        private String username;

        @JsonProperty(value = STORAGE_PASSWORD)
        private String password;

        @JsonProperty(value = STORAGE_HOST)
        private String host;

        @JsonProperty(value = STORAGE_PORT)
        private Integer port;

        @JsonProperty(value = STORAGE_PRIVATE_KEY)
        private String privateKey;

        // Getters and Setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getAuthMethod() {
            return authMethod;
        }

        public void setAuthMethod(String authMethod) {
            this.authMethod = authMethod;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }
    }

    public static class DyteBucketConfig {
        @JsonProperty(value = DYTE_BUCKET_ENABLED)
        private Boolean enabled;

        // Getters and Setters
        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

    public static class LiveStreamingConfig {
        @JsonProperty(value = LIVE_STREAMING_RTMP_URL)
        private String rtmpUrl;

        // Getters and Setters
        public String getRtmpUrl() {
            return rtmpUrl;
        }

        public void setRtmpUrl(String rtmpUrl) {
            this.rtmpUrl = rtmpUrl;
        }
    }

    // Additional DTO for the response structure
    public static class DyteMeetingResponseDTO {
        @JsonProperty(value = SUCCESS)
        private Boolean success;

        @JsonProperty(value = DATA)
        private Data data;

        // Getters and Setters
        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public static class Data {
            @JsonProperty(value = ID)
            private String id;
            
            @JsonProperty(value = TITLE)
            private String title;
            
            @JsonProperty(value = PREFERRED_REGION)
            private String preferredRegion;
            
            @JsonProperty(value = CREATED_AT)
            private ZonedDateTime createdAt;

            @JsonProperty(value = RECORD_ON_START)
            private Boolean recordOnStart;

            @JsonProperty(value = UPDATED_AT)
            private ZonedDateTime updatedAt;

            @JsonProperty(value = LIVE_STREAM_ON_START)
            private Boolean liveStreamOnStart;

            @JsonProperty(value = PERSIST_CHAT)
            private Boolean persistChat;

            @JsonProperty(value = SUMMARIZE_ON_END)
            private Boolean summarizeOnEnd;
            
            @JsonProperty(value = STATUS)
            private String status;
            
            @JsonProperty(value = RECORDING_CONFIG)
            private RecordingConfig recordingConfig;

            // Getters and Setters
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPreferredRegion() {
                return preferredRegion;
            }

            public void setPreferredRegion(String preferredRegion) {
                this.preferredRegion = preferredRegion;
            }

            public ZonedDateTime getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(ZonedDateTime createdAt) {
                this.createdAt = createdAt;
            }

            public Boolean getRecordOnStart() {
                return recordOnStart;
            }

            public void setRecordOnStart(Boolean recordOnStart) {
                this.recordOnStart = recordOnStart;
            }

            public ZonedDateTime getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(ZonedDateTime updatedAt) {
                this.updatedAt = updatedAt;
            }

            public Boolean getLiveStreamOnStart() {
                return liveStreamOnStart;
            }

            public void setLiveStreamOnStart(Boolean liveStreamOnStart) {
                this.liveStreamOnStart = liveStreamOnStart;
            }

            public Boolean getPersistChat() {
                return persistChat;
            }

            public void setPersistChat(Boolean persistChat) {
                this.persistChat = persistChat;
            }

            public Boolean getSummarizeOnEnd() {
                return summarizeOnEnd;
            }

            public void setSummarizeOnEnd(Boolean summarizeOnEnd) {
                this.summarizeOnEnd = summarizeOnEnd;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public RecordingConfig getRecordingConfig() {
                return recordingConfig;
            }

            public void setRecordingConfig(RecordingConfig recordingConfig) {
                this.recordingConfig = recordingConfig;
            }
        }
    }
}
