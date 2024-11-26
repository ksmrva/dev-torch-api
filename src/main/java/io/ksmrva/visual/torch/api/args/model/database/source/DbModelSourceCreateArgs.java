package io.ksmrva.visual.torch.api.args.model.database.source;

import java.math.BigInteger;

public class DbModelSourceCreateArgs {

    private BigInteger sourceUrlId;

    private String driverName;

    private String username;

    private String password;

    public DbModelSourceCreateArgs() {

    }

    public BigInteger getSourceUrlId() {
        return sourceUrlId;
    }

    public void setSourceUrlId(BigInteger sourceUrlId) {
        this.sourceUrlId = sourceUrlId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
