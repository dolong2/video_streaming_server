# video_streaming_server
* 한번 해보고 싶어서 만들어봄


---
### video.html
/video/{videoId}로 접속시 영상 재생됨
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
</head>
<body>
    <video width="320" height="240" autoplay="autoplay" controls>
        <source th:src="'/v1/video/stream/'+${videoId}" src="/videos/${videoName}" type="video/mp4">
    </video>
    <div>
        <p th:text="${video.getTitle()}"></p>
        <p th:text="${video.owner.getName()}"></p>
        <div th:each="comment : *{comments}">
            <p th:text="${comment.getMember().getId()}"></p>
            <p th:text="${comment.getMember().getName()}"></p>
            <p th:text="${comment.getContent()}"></p>
        </div>
    </div>
</body>
</html>
```
