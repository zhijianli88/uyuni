/*
 * Copyright (c) 2020 SUSE LLC
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

package com.redhat.rhn.domain.contentmgmt.modulemd.test;

import com.redhat.rhn.domain.contentmgmt.modulemd.Module;

import junit.framework.TestCase;

public class ModuleTest extends TestCase {

    public void testGetFullName() {
        Module module = new Module("mymodule", "mystream");
        assertEquals("mymodule:mystream", module.getFullName());

        module = new Module("mymodule", "");
        assertEquals("mymodule", module.getFullName());

        module = new Module("mymodule", null);
        assertEquals("mymodule", module.getFullName());
    }
}
