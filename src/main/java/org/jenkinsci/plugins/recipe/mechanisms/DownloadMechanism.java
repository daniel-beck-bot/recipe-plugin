package org.jenkinsci.plugins.recipe.mechanisms;

import hudson.Extension;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.HttpResponses;
import org.kohsuke.stapler.StaplerRequest;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Downloads the XML file as-is.
 *
 * @author Kohsuke Kawaguchi
 */
public class DownloadMechanism extends ExportMechanism {
    @DataBoundConstructor
    public DownloadMechanism() {
    }

    @Override
    public HttpResponse doExport(StaplerRequest req) {
        // redirect once to get the file name in the URL
        return HttpResponses.redirectTo(getRecipe().getFileName());
    }

    /**
     * To serve the recipe under arbitrary file name.
     */
    public HttpResponse doDynamic() throws IOException, ServletException {
        return getRecipe();
    }

    @Extension
    public static class DescriptorImpl extends ExportMechanismDescriptor {
        @Override
        public String getDisplayName() {
            return "Download to your computer";
        }
    }
}
