package net.craftingstore.core.scheduler;

import net.craftingstore.core.CraftingStore;
import net.craftingstore.core.api.CraftingStoreCachedAPI;
import net.craftingstore.core.exceptions.CraftingStoreApiException;

public class APICacheRenewer implements Runnable {

    private CraftingStore instance;

    public APICacheRenewer(CraftingStore instance) {
        this.instance = instance;
    }


    @Override
    public void run() {
        if (!instance.isEnabled()) {
            return;
        }
        if (!(instance.getApi() instanceof CraftingStoreCachedAPI)) {
            return;
        }
        CraftingStoreCachedAPI api = (CraftingStoreCachedAPI) instance.getApi();
        try {
            api.refreshPaymentsCache();
            api.refreshTopDonatorsCache();
        } catch (CraftingStoreApiException e) {
            e.printStackTrace();
        }
    }
}
