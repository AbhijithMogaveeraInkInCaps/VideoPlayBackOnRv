package com.abhijith.videoplaybackonrv.player.datasource;

import com.abhijith.videoplaybackonrv.player.util.HttpHeaders;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Predicate;

/**
 * An implementation of the {@link DefaultHttpDataSource} with a support
 * for the Request Authorization using a dedicated {@link RequestAuthorizer}.
 */
public class ArviHttpDataSource extends DefaultHttpDataSource {


    private RequestAuthorizer requestAuthorizer;


    public ArviHttpDataSource(String userAgent, Predicate<String> contentTypePredicate) {
        super(userAgent, contentTypePredicate);
    }


    public ArviHttpDataSource(String userAgent,
                              Predicate<String> contentTypePredicate,
                              int connectTimeoutMillis,
                              int readTimeoutMillis) {
        super(userAgent, contentTypePredicate, connectTimeoutMillis, readTimeoutMillis);
    }


    public ArviHttpDataSource(
            String userAgent,
            Predicate<String> contentTypePredicate,
            int connectTimeoutMillis,
            int readTimeoutMillis,
            boolean allowCrossProtocolRedirects,
            RequestProperties defaultRequestProperties) {
        super(userAgent, contentTypePredicate, connectTimeoutMillis, readTimeoutMillis, allowCrossProtocolRedirects, defaultRequestProperties);
    }

    public final ArviHttpDataSource setRequestAuthorizer(RequestAuthorizer requestAuthorizer) {
        this.requestAuthorizer = requestAuthorizer;
        return this;
    }

    @Override
    public final long open(DataSpec dataSpec) throws HttpDataSourceException {
        if (requestAuthorizer != null) {
            setRequestProperty(HttpHeaders.AUTHORIZATION, requestAuthorizer.getAuthorization());
        }
        return super.open(dataSpec);
    }
}
