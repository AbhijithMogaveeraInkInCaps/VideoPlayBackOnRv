package com.abhijith.videoplaybackonrv.player.datasource;

import androidx.annotation.NonNull;

/**
 * A base contract to be implemented by the concrete HTTP Video Data Request Authorizers.
 * (Should be able to provide the appropriate authorization token)
 */
public interface RequestAuthorizer {

    /**
     * Used to obtain the appropriate Authorization Token for the Video Data Request.
     *
     * @return the authorization token to be used for the request
     */
    @NonNull
    String getAuthorization();

}
