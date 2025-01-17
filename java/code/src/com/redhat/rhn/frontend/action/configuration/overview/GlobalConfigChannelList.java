/*
 * Copyright (c) 2009--2014 Red Hat, Inc.
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 *
 * Red Hat trademarks are not licensed under GPLv2. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */
package com.redhat.rhn.frontend.action.configuration.overview;

import com.redhat.rhn.common.db.datasource.DataResult;
import com.redhat.rhn.domain.user.User;
import com.redhat.rhn.frontend.listview.PageControl;
import com.redhat.rhn.frontend.struts.RequestContext;
import com.redhat.rhn.frontend.struts.RhnHelper;
import com.redhat.rhn.frontend.struts.RhnListAction;
import com.redhat.rhn.manager.configuration.ConfigurationManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * GlobalConfigChannelList
 */
public class GlobalConfigChannelList extends RhnListAction {

    /**
     * {@inheritDoc}
     */
    public final ActionForward execute(ActionMapping mapping,
            ActionForm formIn,
            HttpServletRequest request,
            HttpServletResponse response) {

        RequestContext requestContext = new RequestContext(request);

        User user = requestContext.getCurrentUser();
        PageControl pc = new PageControl();
        pc.setFilterColumn("name");
        pc.setFilter(true);

        clampListBounds(pc, request, user);
        request.setAttribute(RequestContext.PAGE_LIST, getDataResult(user, pc));
        return getStrutsDelegate().forwardParams(mapping.findForward(
                RhnHelper.DEFAULT_FORWARD), request.getParameterMap());

    }

    /**
     * Gets a data result containing all of the global channels the given user can see.
     * @param user The user requesting to see channels (logged in User)
     * @param pc A page control for this user
     * @return A list of Config Channels as a DTO
     */
    private DataResult getDataResult(User user, PageControl pc) {
        return ConfigurationManager.getInstance().listGlobalChannels(user, pc);
    }

}
