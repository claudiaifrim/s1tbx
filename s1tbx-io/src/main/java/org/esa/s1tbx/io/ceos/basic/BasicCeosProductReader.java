/*
 * Copyright (C) 2015 by Array Systems Computing Inc. http://www.array.ca
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see http://www.gnu.org/licenses/
 */
package org.esa.s1tbx.io.ceos.basic;

import com.bc.ceres.core.VirtualDir;
import org.esa.s1tbx.io.ceos.CEOSProductDirectory;
import org.esa.s1tbx.io.ceos.CEOSProductReader;
import org.esa.snap.core.dataio.DecodeQualification;
import org.esa.snap.core.dataio.ProductReaderPlugIn;

import java.io.File;

/**
 * The product reader for CEOS products.
 */
public class BasicCeosProductReader extends CEOSProductReader {

    /**
     * Constructs a new abstract product reader.
     *
     * @param readerPlugIn the reader plug-in which created this reader, can be <code>null</code> for internal reader
     *                     implementations
     */
    public BasicCeosProductReader(final ProductReaderPlugIn readerPlugIn) {
        super(readerPlugIn);
    }

    @Override
    protected CEOSProductDirectory createProductDirectory(final VirtualDir productDir) {
        return new BasicCeosProductDirectory(productDir);
    }

    DecodeQualification checkProductQualification(File file) {

        try {
            dataDir = createProductDirectory(createProductDir(file));

            final BasicCeosProductDirectory dataDir = (BasicCeosProductDirectory) this.dataDir;
            if (dataDir.isCeos())
                return DecodeQualification.SUITABLE;
            return DecodeQualification.UNABLE;

        } catch (Exception e) {
            return DecodeQualification.UNABLE;
        }
    }
}
