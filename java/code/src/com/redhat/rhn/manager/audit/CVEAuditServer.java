/*
 * Copyright (c) 2013 SUSE LLC
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
package com.redhat.rhn.manager.audit;

import com.redhat.rhn.frontend.struts.SelectableAdapter;

import java.util.Set;

/**
 * Class representing a record with data about a single system containing that system's
 * patch status regarding a certain given CVE identifier as well as sets of relevant
 * channels and erratas.
 *
 */
public class CVEAuditServer extends SelectableAdapter implements CVEAuditSystem {

    private long id;
    private String name;
    private PatchStatus patchStatus;

    // LinkedHashSet is used to preserve insertion order when iterating
    private Set<ChannelIdNameLabelTriple> channels;
    private Set<ErrataIdAdvisoryPair> erratas;

    /**
     * Constructor
     * @param idIn id
     * @param nameIn name
     * @param statusIn status
     * @param channelsIn channels
     * @param erratasIn errata
     */
    public CVEAuditServer(long idIn, String nameIn, PatchStatus statusIn,
                          Set<ChannelIdNameLabelTriple> channelsIn,
                          Set<ErrataIdAdvisoryPair> erratasIn) {
        this.id = idIn;
        this.name = nameIn;
        this.patchStatus = statusIn;
        this.channels = channelsIn;
        this.erratas = erratasIn;
    }

    /**
     * return the rank
     * @return the rank
     */
    public long getPatchStatusRank() {
       return patchStatus.getRank();
    }

    @Override
    public String getSelectionKey() {
        return String.valueOf(getId());
    }

    /**
     * Return the system ID.
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Return the system name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the patch status.
     * @return the patchStatus
     */
    public PatchStatus getPatchStatus() {
        return patchStatus;
    }

    /**
     * Return the set of channels.
     * @return the channels
     */
    public Set<ChannelIdNameLabelTriple> getChannels() {
        return channels;
    }

    /**
     * Return the set of erratas.
     * @return the erratas
     */
    public Set<ErrataIdAdvisoryPair> getErratas() {
        return erratas;
    }


}
