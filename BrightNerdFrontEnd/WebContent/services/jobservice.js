/**
 * JobService
 *
 */
app.factory('JobService',function($http){
	var jobService={}
	var BASE_URL="http://localhost:8081/BrightNerdMiddleWare"
	jobService.addJob=function(job){
		return $http.post(BASE_URL + "/addjob",job)
	}
	
	jobService.getAllJobs=function(){
		return $http.get(BASE_URL + "/getalljobs")
	}
	
	return jobService;
})