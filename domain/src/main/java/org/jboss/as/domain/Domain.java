/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import org.jboss.as.model.AbstractModel;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * The JBoss AS Domain state.  An instance of this class represents the complete running state of the domain.
 * <p/>
 * All updates to the domain occur via instances of the {@link AbstractDomainUpdate} class.  There is no other public
 * mutability of this class.
 * <p/>
 * Instances of this class are guaranteed to be thread-safe in the presence of invocations from multiple threads.
 *
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public final class Domain extends AbstractModel<Domain> {

    private static final long serialVersionUID = 5516070442013067881L;

    public static final String NAMESPACE_1_0 = "urn:jboss:domain:1.0";

    /**
     * The set of recognized domain namespaces.
     */
    public static final Set<String> NAMESPACES = Collections.singleton(NAMESPACE_1_0);

    private transient final NavigableMap<String, DomainServerGroup> serverGroups = new TreeMap<String, DomainServerGroup>();
    private transient final NavigableMap<String, AbstractDomainDeployment<?>> deployments = new TreeMap<String, AbstractDomainDeployment<?>>();
    private transient final NavigableMap<String, DomainSubsystem> subsystems = new TreeMap<String, DomainSubsystem>();

    public Domain(final String id) {
        super(id);
    }

    /** {@inheritDoc} */
    public long checksum() {
        throw new IllegalStateException();
    }

    /**
     * Calculate the difference (in updates) between this model and another one.
     *
     * @param other the other model
     * @return the collection of updates which, when applied in order, would bring this model to equivalency with the other
     */
    public Collection<AbstractDomainUpdate<?>> getDifference(Domain other) {
        if (other == null) {
            throw new IllegalArgumentException("other is null");
        }
        final List<AbstractDomainUpdate<?>> list = new ArrayList<AbstractDomainUpdate<?>>();
        
        return list;
    }

    public void writeObject(final XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeComment(
                "!!! NOTE !!!\n\n" +
                "This file is generated and managed by the\n" +
                "Domain Controller and should only be edited when\n" +
                "it is offline."
        );
        streamWriter.writeStartElement(NAMESPACE_1_0, "domain");
        final String id = getId();
        if (id != null) streamWriter.writeAttribute("id", id);
        streamWriter.writeEndElement();
    }
}
