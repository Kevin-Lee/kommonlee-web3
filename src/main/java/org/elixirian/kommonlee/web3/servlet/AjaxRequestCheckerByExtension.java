/**
 * This project is licensed under the Apache License, Version 2.0
 * if the following condition is met:
 * (otherwise it cannot be used by anyone but the author, Kevin, only)
 *
 * The original KommonLee project is owned by Lee, Seong Hyun (Kevin).
 *
 * -What does it mean to you?
 * Nothing, unless you want to take the ownership of
 * "the original project" (not yours or forked & modified one).
 * You are free to use it for both non-commercial and commercial projects
 * and free to modify it as the Apache License allows.
 *
 * -So why is this condition necessary?
 * It is only to protect the original project (See the case of Java).
 *
 *
 * Copyright 2009 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.elixirian.kommonlee.web3.servlet;

import static org.elixirian.kommonlee.util.Strings.*;

import javax.servlet.http.HttpServletRequest;

import org.elixirian.kommonlee.type.functional.Function1;

/**
 * <pre>
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2012-04-01)
 */
public class AjaxRequestCheckerByExtension implements AjaxRequestChecker
{
	public static final String DEFAULT_AJAX_EXTENSION = ".json";

	private final String ajaxExtension;

	private final Function1<String, String> caseTakeCareFunction;

	public AjaxRequestCheckerByExtension(final String ajaxExtension, final boolean caseSensitive)
	{
		if (caseSensitive)
		{
			this.caseTakeCareFunction = new Function1<String, String>() {
				@Override
				public String apply(final String url)
				{
					return url;
				}
			};
			this.ajaxExtension = nullSafeTrim(ajaxExtension);
		}
		else
		{
			this.caseTakeCareFunction = new Function1<String, String>() {
				@Override
				public String apply(final String url)
				{
					return url.toLowerCase();
				}
			};
			this.ajaxExtension = nullSafeTrim(ajaxExtension).toLowerCase();
		}
	}

	@Override
	public boolean isAjax(final HttpServletRequest request)
	{
		/* @formatter:off */
    return caseTakeCareFunction.apply(request
                                      .getRequestURI())
            .endsWith(ajaxExtension);
    /* @formatter:on */
	}

}
