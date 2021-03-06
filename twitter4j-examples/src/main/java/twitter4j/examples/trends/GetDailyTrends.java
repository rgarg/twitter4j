/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package twitter4j.examples.trends;

import twitter4j.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Shows daily trends.
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class GetDailyTrends {
    /**
     * Usage: java twitter4j.examples.trends.GetDailyTrends [yyyy-mm-dd]
     *
     * @param args message
     */
    public static void main(String[] args) {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            List<Trends> trendsList;
            if (args.length > 0) {
                trendsList = twitter.getDailyTrends(new SimpleDateFormat("yyyy-MM-dd").parse(args[0]), false);

            } else {
                trendsList = twitter.getDailyTrends();
            }
            System.out.println("Showing daily trends");
            for (Trends trends : trendsList) {
                System.out.println("As of : " + trends.getAsOf());
                for (Trend trend : trends.getTrends()) {
                    System.out.println(" " + trend.getName());
                }
            }
            System.out.println("done.");
            System.exit(0);
        } catch (ParseException pe) {
            System.out.println("Usage: java twitter4j.examples.trends.GetDailyTrends [yyyy-mm-dd]");
            System.exit(-1);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get trends: " + te.getMessage());
            System.exit(-1);
        }
    }
}
