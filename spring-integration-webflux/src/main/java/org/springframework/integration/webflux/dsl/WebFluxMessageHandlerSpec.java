/*
 * Copyright 2017 the original author or authors.
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

package org.springframework.integration.webflux.dsl;

import java.net.URI;

import org.springframework.expression.Expression;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.expression.ValueExpression;
import org.springframework.integration.http.dsl.BaseHttpMessageHandlerSpec;
import org.springframework.integration.webflux.outbound.WebFluxRequestExecutingMessageHandler;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The {@link BaseHttpMessageHandlerSpec} implementation for the {@link WebFluxRequestExecutingMessageHandler}.
 *
 * @author Shiliang Li
 * @author Artem Bilan
 *
 * @since 5.0
 *
 * @see WebFluxRequestExecutingMessageHandler
 */
public class WebFluxMessageHandlerSpec
		extends BaseHttpMessageHandlerSpec<WebFluxMessageHandlerSpec, WebFluxRequestExecutingMessageHandler> {

	private final WebClient webClient;

	WebFluxMessageHandlerSpec(URI uri, WebClient webClient) {
		this(new ValueExpression<>(uri), webClient);
	}

	WebFluxMessageHandlerSpec(String uri, WebClient webClient) {
		this(new LiteralExpression(uri), webClient);
	}

	WebFluxMessageHandlerSpec(Expression uriExpression, WebClient webClient) {
		super(new WebFluxRequestExecutingMessageHandler(uriExpression, webClient));
		this.webClient = webClient;
	}

	@Override
	protected boolean isClientSet() {
		return this.webClient != null;
	}

	@Override
	protected WebFluxMessageHandlerSpec expectReply(boolean expectReply) {
		return super.expectReply(expectReply);
	}

}
