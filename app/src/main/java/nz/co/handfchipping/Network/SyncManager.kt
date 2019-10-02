package nz.co.handfchipping.Network

class SyncManager() {
    companion object {
        @Volatile
        private var INSTANCE: SyncManager? = null

        fun getInstance(): SyncManager {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = SyncManager()
                INSTANCE = instance
                return instance
            }
        }
    }
}