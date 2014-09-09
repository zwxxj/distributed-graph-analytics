package com.soteradefense.dga;

import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.yarn.conf.YarnConfiguration;

import java.security.PrivilegedAction;

public class DGAYarnRunner {
    public static void main(final String [] args) throws Exception {
        if (!UserGroupInformation.isSecurityEnabled()) {
            UserGroupInformation.createRemoteUser(YarnConfiguration.DEFAULT_NM_NONSECURE_MODE_LOCAL_USER).doAs(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    try {
                        DGARunner runner = new DGARunner();
                        runner.run(args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
        } else {
            DGARunner runner = new DGARunner();
            runner.run(args);
        }
    }
}
