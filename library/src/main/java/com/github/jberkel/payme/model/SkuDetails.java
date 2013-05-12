/* Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jberkel.payme.model;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents an in-app product's listing details.
 */
public class SkuDetails {
    private final ItemType mItemType;
    private final String mSku;
    private final String mType;
    private final String mPrice;
    private final String mTitle;
    private final String mDescription;
    private String mJson;

    public SkuDetails(String jsonSkuDetails) throws JSONException {
        this(ItemType.INAPP, jsonSkuDetails);
    }

    public SkuDetails(ItemType itemType, String jsonSkuDetails) throws JSONException {
        if (itemType == null) throw new IllegalArgumentException("itemType cannot be null");

        mItemType = itemType;
        mJson = jsonSkuDetails;
        JSONObject o = new JSONObject(mJson);
        mSku = o.optString("productId");
        mType = o.optString("type");
        mPrice = o.optString("price");
        mTitle = o.optString("title");
        mDescription = o.optString("description");

        if (TextUtils.isEmpty(mSku)) {
            throw new JSONException("SKU cannot be empty");
        }
    }

    public SkuDetails(ItemType itemType, String sku, String type, String price, String title, String description) {
        if (itemType == null) throw new IllegalArgumentException("itemType cannot be null");
        if (TextUtils.isEmpty(sku)) {
            throw new IllegalArgumentException("SKU cannot be empty");
        }
        mItemType = itemType;
        mSku = sku;
        mType = type;
        mPrice = price;
        mTitle = title;
        mDescription = description;
    }

    public String getSku() { return mSku; }
    public String getType() { return mType; }
    public String getPrice() { return mPrice; }
    public String getTitle() { return mTitle; }
    public String getDescription() { return mDescription; }

    @Override
    public String toString() {
        return "SkuDetails{" +
                "mItemType='" + mItemType + '\'' +
                ", mSku='" + mSku + '\'' +
                ", mType='" + mType + '\'' +
                ", mPrice='" + mPrice + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mJson='" + mJson + '\'' +
                '}';
    }
}
