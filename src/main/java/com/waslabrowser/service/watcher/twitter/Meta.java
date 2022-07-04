
package com.waslabrowser.service.watcher.twitter;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Meta {

    @SerializedName("next_token")
    private String mNextToken;
    @SerializedName("result_count")
    private Long mResultCount;

    public String getNextToken() {
        return mNextToken;
    }

    public void setNextToken(String nextToken) {
        mNextToken = nextToken;
    }

    public Long getResultCount() {
        return mResultCount;
    }

    public void setResultCount(Long resultCount) {
        mResultCount = resultCount;
    }

}
