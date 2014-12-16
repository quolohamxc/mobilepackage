/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.util;

import ws.model.Page;
import static ws.util.HttpUtils.fetchHttpResponse;

/**
 *
 * @author floion z
 */
 class HttpRetry extends Retry<Page> {

        String url;
        ConnectionConfig conconfig;

        public HttpRetry(String url, ConnectionConfig conconfig) {
            this.url = url;
            this.conconfig = conconfig;
        }

        @Override
        public Page run() throws Exception {
            return fetchHttpResponse(url, conconfig);
        }

    }