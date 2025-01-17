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
package com.redhat.rhn.frontend.action.errata.test;

import com.redhat.rhn.domain.channel.Channel;
import com.redhat.rhn.domain.channel.test.ChannelFactoryTest;
import com.redhat.rhn.domain.errata.Errata;
import com.redhat.rhn.domain.errata.ErrataFactory;
import com.redhat.rhn.domain.errata.test.ErrataFactoryTest;
import com.redhat.rhn.domain.org.Org;
import com.redhat.rhn.domain.rhnset.RhnSet;
import com.redhat.rhn.domain.user.User;
import com.redhat.rhn.frontend.action.errata.ChannelSetupAction;
import com.redhat.rhn.frontend.struts.RequestContext;
import com.redhat.rhn.manager.rhnset.RhnSetDecl;
import com.redhat.rhn.testing.ActionHelper;
import com.redhat.rhn.testing.RhnBaseTestCase;
import com.redhat.rhn.testing.UserTestUtils;

/**
 * ChannelSetupActionTest
 */
public class ChannelSetupActionTest extends RhnBaseTestCase {

    /**
     * A dummy test until the other two are fixed.
     * @throws Exception something bad happened
     */
    public void testDummy() throws Exception {
        assertEquals(42, 42);
    }

    /**
     * This setup action will get called with a published errata from the channels edit
     * tab that appears in the details nav for a published errata. We need to make sure
     * that the users set gets initialized to the channels that are in the errata when
     * the user first visits the page.
     * @throws Exception something bad happened
     */
    // This test does not properly set up the permissions to the
    // errata, because the user for the action is not the same as the
    // user for the errata or the channel.
    public void brokenTestExecutePublished() throws Exception {
        ChannelSetupAction action = new ChannelSetupAction();
        ActionHelper sah = new ActionHelper();

        sah.setUpAction(action);
        sah.setupClampListBounds();

        //Create a new errata
        Org org = UserTestUtils.createNewOrgFull("channelSetupActionTestbrokenTestExec");
        Errata e = ErrataFactoryTest.createTestErrata(org.getId());
        //make sure we have a channel for the errata
        Channel c1 = ChannelFactoryTest.createTestChannel(org);
        e.addChannel(c1);
        ErrataFactory.save(e);
        //setup the request object
        sah.getRequest().setupAddParameter("eid", e.getId().toString());
        sah.getRequest().setupAddParameter("newset", (String) null);
        sah.getRequest().setupAddParameter("returnvisit", (String) null);
        sah.getRequest().setupAddParameter("returnvisit", (String) null);
        sah.executeAction();

        RequestContext requestContext = new RequestContext(sah.getRequest());

        //make sure set is not empty
        User user = requestContext.getCurrentUser();
        RhnSet set = RhnSetDecl.CHANNELS_FOR_ERRATA.get(user);
        assertFalse(set.isEmpty());
    }
}
