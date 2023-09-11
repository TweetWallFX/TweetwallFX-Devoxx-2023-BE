/*
 * MIT License
 *
 * Copyright (c) 2022-2023 TweetWallFX
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tweetwallfx.conference.impl;

import static org.tweetwallfx.util.ToString.createToString;
import static org.tweetwallfx.util.ToString.map;

import java.util.Objects;

import org.tweetwallfx.config.ConfigurationConverter;

/**
 * POJO for reading Settings concerning Conference Client.
 */
public class ConferenceClientSettings {

    /**
     * Configuration key under which the data for this Settings object is stored
     * in the configuration data map.
     */
    public static final String CONFIG_KEY = "conferenceClient";
    private String eventBaseUri;
    private String votingResultsToken;

    /**
     * Returns the Event Base URI from where all standard calls are executed.
     *
     * @return the Event Base URI
     */
    public String getEventBaseUri() {
        return Objects.requireNonNull(eventBaseUri, "eventBaseUri must not be null!");
    }

    /**
     * Sets the Event Base URI from where all standard calls are executed.
     *
     * @param eventBaseUri the Event Base URI
     */
    public void setEventBaseUri(final String eventBaseUri) {
        Objects.requireNonNull(eventBaseUri, "eventBaseUri must not be null!");
        this.eventBaseUri = eventBaseUri.endsWith("/")
                ? eventBaseUri
                : eventBaseUri + '/';
    }

    /**
     * Returns the Token required to retrieve voting results.
     *
     * @return the voting result token
     */
    public String getVotingResultsToken() {
        return votingResultsToken;
    }

    /**
     * Sets the Token required to retrieve voting results.
     *
     * @param votingResultsToken the voting result token
     */
    public void setVotingResultsToken(String votingResultsToken) {
        Objects.requireNonNull(votingResultsToken, "votingResultsToken must not be null!");
        this.votingResultsToken = votingResultsToken;
    }

    @Override
    public String toString() {
        return createToString(this, map(
                "eventBaseUri", getEventBaseUri(),
                "votingResultsToken", getVotingResultsToken()
        )) + " extends " + super.toString();
    }

    /**
     * Service implementation converting the configuration data of the root key
     * {@link ConferenceClientSettings#CONFIG_KEY} into
     * {@link ConferenceClientSettings}.
     */
    public static class Converter implements ConfigurationConverter {

        @Override
        public String getResponsibleKey() {
            return ConferenceClientSettings.CONFIG_KEY;
        }

        @Override
        public Class<?> getDataClass() {
            return ConferenceClientSettings.class;
        }
    }
}
