//package snap
//
//class DownloadManager {
//}
//
//import java.util.*
//
///**
// * Chat - Feed - Camera - Story - Spotlight
// **/
//class DownloadManager : PageChangedCallback, NetworkRequestCallback {
//    private val NetworkExecutor: NetworkExecutor? = null
//    private val requestQueue = PriorityQueue<DownloadRequest> {
//            first, second -> second.getPriority() - first.getPriority()
//    }
//
//    val ownerMap = HashMap<String, Set<DownloadRequest>>()
//
//    @Synchronized
//    fun submit(request: DownloadRequest ) {
//        // add it to the priotiry queue
//        ownerMap.putIfAbsent(request.getOwner(), mutableSetOf())
//        ownerMap[request.getOwner()]!!.add(request)
//
//        requestQueue.add(request)
//    }
//
//    @Synchronized
//    override fun onPageChanged(from: String, to: String) {
//        var requestsToDepri = ownerMap[from]
//        var requestsToPri = ownerMap[to]
//
//        for(request in requestsToDepri) {
//
//            requestQueue.remove(rquest)
//            request.setOwnerActive(false)
//            requestQueue.add(request)
//        }
//
//        for(request in requestsToPri) {
//            requestQueue.remove(rquest)
//            request.setOwnerActive(true)
//            requestQueue.add(request)
//        }
//    }
//
//    override fun onNetworkRequestComplete(networkRequest: NetworkRequest) {
//        networkRequest.requestCompleteCallback()
//    }
//
//    @Synchronized
//    fun getNextRequest(): NetworkRequest {
//        val rquest = requestQueue.poll()
//        return request.getNetworkRquest()
//    }
//
//}
//
//
//enum class RequestResourceType(val priority: RequestPriority) {
//    TEXT(HIGH),
//    IMAGE(MID),
//    VIDEO(LOW),
//}
//
//enum class RequestPriority {
//    HIGH,
//    MID,
//    LOW
//}
//
//class Request {
//    val resourceType: RequestResourceType
//
//    fun setOwnerActive(isActive: Boolean)
//    fun getPriority(): Int
//}
//
//class DownloadRequest: Request {
//    var networkRequest: NetworkRequest? = null
//    fun getOwner(): String // page
//    fun getInitialPriority()
//}
//
//interface NetworkExecutor {
//    fun execute(networkRequest: NetworkRequest?)
//}
//
//class NetworkRequest {
//    fun equals():
//    val requestCompleteCallback: RequestCompleteCallback
//    // fun getOwner(): String
//}
//
//interface PageChangedCallback {
//    fun onPageChanged(from: String, to: String)
//}
//
//interface NetworkRequestCallback {
//    fun onNetworkRequestComplete(networkRequest: NetworkRequest)
//}
//
//interface DownloadCompleteCallback {
//    fun downloadCompleted()
//}
//interface RequestCompleteCallback {
//    completed()
//}