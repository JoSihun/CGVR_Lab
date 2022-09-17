# CGVR_Lab
SeoKyeong University CGVR Lab Webpage.  
최신 컴퓨터 그래픽스 및 가상현실 기술 응용에 관한 연구 수행을 위한 연구실 홈페이지  
[[HyperLink](https://skuniv-cgvrlab.kro.kr)]: https://skuniv-cgvrlab.kro.kr


## 1. CO-Development Environment


### 1. 1 Environments
- Windows 10
- Ubuntu 20.04 LTS (Server)
- MySQL
- Spring Boot / Java 11
- HTML / CSS / JavaScript
- BootStrap / Mustache / NginX


### 1. 2 Ubuntu Server Setting
![image](https://user-images.githubusercontent.com/59362257/190059975-3c2cd753-c353-4e1f-b116-60123b2bec50.png)
- `Ubuntu Server` 공동개발환경 사용
  - `Local Client`: 개발자 PC
  - `Ubuntu Server`: 서버 PC, `Local Client`와 SSH Protocol 연결
  - `MySQL`: `Client`와 `Server` 동일하게 Setting
  - `CGVRLAB`: `MySQL` Schema
- `Github` 공동개발환경 사용
  - `Client`에서 개발 후 `git push`
  - `Server`에서 `git pull`



### 1. 3 NginX Server Setting
![image](https://user-images.githubusercontent.com/59362257/190060634-becfd186-47bc-4175-91cf-893d22020344.png)
- `Client`로부터 `80 Port`에 `Request` 요청
- `NginX`: `80 Port`로 들어온 요청을 `8080 Port`로 연결
- `Java`: `Spring Boot Project`를 `8080 Port`로 서비스
- `8080 Port`에서 `Request` 처리, `NginX`로 `Response` 전달
- `NginX`: `Client`에게 `Response` 전달



## 2. Main Architecture


### 2. 1 MVC Pattern Architecture
#### 2. 1. 1 MVC Pattern   
- Model-View-Controller 의 약자로, 애플리케이션을 세 가지 역할로 구분한 개발 방법론   
- `Model` : 데이터, 정보들의 가공을 책임지는 컴포넌트. 애플리케이션의 정보, 데이터를 나타냄   
- `View` : 사용자 인터페이스(UI) 요소를 의미함   
- `Controller` : Model 과 View 사이를 이어주는 브릿지(Bridge)역할을 수행함   
- 비즈니스 로직과 UI 로직을 분리하여 유지보수를 독립적으로 수행할 수 있음
- `Model` 과 `View` 가 다른 컴포넌트들에 종속되지 않아 애플리케이션의 확장성, 유연성에 유리함
- 중복 코딩의 문제점이 제거됨

![image](https://user-images.githubusercontent.com/74171272/190120481-ae48ab3e-cc97-49ea-b136-bd5a6794b520.png)
- MVC WEB 에 적용할 경우 예시
  - 사용자가 웹사이트에 접속(Uses)
  - `Controller` 는 사용자가 요청한 웹페이지를 서비스하기 위해서 `Model` 을 호출함(Manipulates)
  - `Model` 은 데이터베이스나 파일과 같은 데이터 소스를 제어한 후 그 결과를 반환함
  - `Controller` 는 `Model` 이 반환한 결과를 `View` 에 반영함(Updates)
  - 데이터가 반영된 `View` 가 사용자에게 보여짐(Sees)

#### 2. 1. 2 MVC Model 1   
![image](https://user-images.githubusercontent.com/74171272/190120616-011b9e75-ae6f-40b7-9d3f-2477c5620f6d.png)
- `Controller` 영역에 `View` 영역을 같이 구현하는 방식.
- `View` 와 `Controller` 를 모두 `JSP` 가 담당하는 형태를 가지므로, 구현 난이도는 쉬움
- `JSP` 하나에서 MVC 가 모두 이루어져, 재사용성, 가독성 하락의 문제점이 존재함 => 유지보수에 문제가 발생함   

#### 2. 1. 3 MVC Model 2   
![image](https://user-images.githubusercontent.com/74171272/190120670-85f81b4e-da8f-4a3a-a57c-532ec1655b74.png)
- 널리 표준으로 사용되는 패턴. `Spring Framework` 에서도 채택한 패턴
- MVC Model 1 과 달리 `Controller` 와 `View` 가 분리되어 MVC Model 1 의 단점 보완 가능
- `Model`, `View`, `Controller` 중 수정해야할 부분이 있다면, 해당 부분만 꺼내어 수정하면 되므로, 유지보수에서 큰 이점을 가짐

### 2. 2 Spring Service Architecture
![image](https://user-images.githubusercontent.com/74171272/190108990-c9d62ce8-a9b5-44b5-8f37-83b25f5855ab.png)
- `Entity(Domain)`
  - DB 에 쓰일 컬럼과 여러 `Entity` 간 연관관계를 정의함
  - DB 의 테이블을 하나의 `Entity` 로 생각해도 무방함
- `Controller`
  - `Client`의 요청을 받아 RequestMapping 을 수행하고, 응답을 전달함
- `Service`
  - 비즈니스 로직과 트랜잭션을 처리함
- `Repository`
  - `Entitiy`에 의해 생성된 `DB`에 접근하는 메소드를사용하기 위한 인터페이스
  - `Service` 와 `DB` 를 연결하는 고리의 역할을 수행
  - `DB`에 접근하는 객체인 `DAO(Data Access Object)` 영역으로 이해하면 됨

- `DTO(Data Transfer Object`
  - 계층 간 데이터 교환을 위한 객체
  - `Entity` 와 유사하지만, `Service` 에서 수정이 가능하므로, `DB` 의 칼럼과는 독립적임

### 2. 3 Restul API
![image](https://user-images.githubusercontent.com/74171272/190115632-076b67f8-262a-4336-af7c-e5f95cee66bd.png)
- `REST(Representational State Transfer)`
  - HTTP URI(Uniform Resource Identifier) 를 통해 리소스를 명시하고, HTTP Method(POST, GET, PUT, DELETE 등)를 통해 해당 리소스에 대한 CRUD 연산을 적용하는 것을 의미함
  - 즉, 어떤 리소스에 대해 CRUD 연산을 수행하기 위해 URI 로 HTTP Method 를 사용하여 요청을 보내는 것

- `REST` 구성 요소
  - 자원(Resouce) : HTTP URI
  - 자원에 대한 행위(Verb) : HTTP Method
  - 자원에 대한 행위의 내용(Representations) : HTTP Message Pay Load

- `REST` 제약조건
  - Server-Client(서버-클라이언트 구조)
  - Stateless(무상태)
  - Cacheable(캐시 처리 가능)
  - Layered System(계층화)
  - Uniform Interface(인터페이스 일관성)

- `RESTful API`
  - `REST` 아키텍처 스타일의 제약조건을 따르는 API

- Spring RestController
  - 아래와 같이 `@RestController` 어노테이션을 사용하여 RestController 를 정의함
  ```java
  @RequiredArgsConstructor
  @RestController
  public class PostsRestController {
    ...
  }
  ```
  
  - POST(Create)
    - 아래와 같이 @PostMapping 어노테이션을 사용하여 POST 메소드를 정의함
    ```java
        @PostMapping("posts/api/save")
        public Long postsSave(PostsSaveRequestDto requestDto, List<MultipartFile> files) throws Exception {
            /* 카테고리명 존재유무 확인 및 저장 */
            if (requestDto.getCategoryName() != null) {
                CategoryResponseDto categoryResponseDto = this.categoryService.findByCategoryName(requestDto.getCategoryName());
                if (categoryResponseDto == null) {
                    CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
                    categorySaveRequestDto.setCategoryName(requestDto.getCategoryName());
                    this.categoryService.save(categorySaveRequestDto);
                }
            }

            /* 프로젝트명 존재유무 확인 및 저장 */
            if (requestDto.getProjectName() != null){
                ProjectResponseDto projectResponseDto = this.projectService.findByProjectName(requestDto.getProjectName());
                if (projectResponseDto == null) {
                    ProjectSaveRequestDto projectSaveRequestDto = new ProjectSaveRequestDto();
                    projectSaveRequestDto.setProjectName(requestDto.getProjectName());
                    this.projectService.save(projectSaveRequestDto);
                }
            }

            return this.postsService.save(requestDto, files);
        }
    ```
    
    - Ajax 통신을 이용하여 클라이언트와 서버 간 데이터 교환
    ```javascript
    postsSave : function () {
        let form = $('#fileUploadForm')[0];
        let formData = new FormData(form);
        formData.append("title", $('#title').val());
        formData.append("author", $('#author').val());
        formData.append("content", $('#content').val());
        formData.append("projectName", $('#projectName').val());
        formData.append("categoryName", $('#categoryName').val());

        $.ajax({
            type: 'POST',
            url: '/posts/api',
            enctype: 'multipart/form-data',
            data: formData,
            dataType: 'json',
            processData: false,
            contentType: false,
        }).done(function (response) {
            alert('글이 등록되었습니다.');
            let arrayLink = document.location.href.split('/').slice(3, -1);
            let stringLink = arrayLink.join('/');
            let redirectUrl = '/' + stringLink + '/' + response;
            window.location.href = redirectUrl;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
    ```

  - PUT(Update)
    - 아래와 같이 `@PutMapping` 어노테이션을 사용하여 Put 메소드를 정의함
    - `@PathVariable` 어노테이션을 사용하여 수정할 객체를 가져옴
    ```java
        @PutMapping("/posts/api/{id}")
        public Long postsUpdate(@PathVariable Long id, PostsUpdateRequestDto requestDto, List<MultipartFile> files) throws Exception {
            /* 카테고리명 존재유무 확인 및 저장 */
            if (requestDto.getCategoryName() != null) {
                CategoryResponseDto categoryResponseDto = this.categoryService.findByCategoryName(requestDto.getCategoryName());
                if (categoryResponseDto == null) {
                    CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
                    categorySaveRequestDto.setCategoryName(requestDto.getCategoryName());
                    this.categoryService.save(categorySaveRequestDto);
                }
            }

            /* 프로젝트명 존재유무 확인 및 저장 */
            if (requestDto.getProjectName() != null){
                ProjectResponseDto projectResponseDto = this.projectService.findByProjectName(requestDto.getProjectName());
                if (projectResponseDto == null) {
                    ProjectSaveRequestDto projectSaveRequestDto = new ProjectSaveRequestDto();
                    projectSaveRequestDto.setProjectName(requestDto.getProjectName());
                    this.projectService.save(projectSaveRequestDto);
                }
            }

            return this.postsService.update(id, requestDto, files);
        }
    ```
    
    - Ajax 통신을 이용하여 클라이언트와 서버 간 데이터 교환
    ```javascript
    postsUpdate : function () {
        let postsId = $('#id').val();
        let form = $('#fileUploadForm')[0];
        let formData = new FormData(form);
        formData.append("title", $('#title').val());
        formData.append("content", $('#content').val());
        formData.append("projectName", $('#projectName').val());
        formData.append("categoryName", $('#categoryName').val());

        $.ajax({
            type: 'PUT',
            url: '/posts/api/'+postsId,
            enctype: 'multipart/form-data',
            data: formData,
            dataType: 'json',
            processData: false,
            contentType: false,
        }).done(function (response) {
            alert('글이 수정되었습니다.');
            let arrayLink = document.location.href.split('/').slice(3, -2);
            let stringLink = arrayLink.join('/');
            let redirectUrl = '/' + stringLink + '/' + response;
            window.location.href = redirectUrl;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
    ```

  - Delete
    - 아래와 같이 `@DeleteMapping` 어노테이션을 사용하여 Delete 메소드를 정의함
    - `@PathVariable` 어노테이션을 사용하여 삭제할 객체를 가져옴
    ```java
        @DeleteMapping("/posts/api/{id}")
        public Long postsDelete(@PathVariable Long id) {
            postsService.delete(id);
            return id;
        }
    ```

    - Ajax 통신을 이용하여 클라이언트와 서버 간 데이터 교환
    ```javascript
    postsDelete : function () {
        var id = $('#postsId').val();

        $.ajax({
            type: 'DELETE',
            url: '/posts/api/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('글이 삭제되었습니다.');
            var arrayLink = document.location.href.split('/').slice(3, -2);
            var stringLink = arrayLink.join('/');
            var redirectUrl = '/' + stringLink + '/board';
            console.log(redirectUrl);
            window.location.href = redirectUrl;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
    ```

### 2. 4 URL Connection
![image](https://user-images.githubusercontent.com/77912766/190369844-b7253af2-4f19-44d5-8b42-f18582d59771.png)




## 3. DataBase


### 3. 1 Table
<p align="center">
  <img width="40%" src="https://user-images.githubusercontent.com/59362257/190083429-02fe88f5-e7ac-4de7-949f-2832208b734d.png" />
  <img width="40%" src="https://user-images.githubusercontent.com/59362257/190090946-f82f256d-6fae-491e-9d49-1c4f9c65319b.png" />
  <img width="40%" src="https://user-images.githubusercontent.com/59362257/190092094-859ba283-992d-4179-8902-3f8435d56d79.png" />
  <img width="40%" src="https://user-images.githubusercontent.com/59362257/190092710-03c066c5-d422-4e36-b40a-5cfc983c3130.png" />
  <img width="40%" src="https://user-images.githubusercontent.com/59362257/190084090-ebe1d3b8-29e1-451b-a4de-6d80c6a8a9e9.png" />
  <img width="40%" src="https://user-images.githubusercontent.com/59362257/190084248-b57da527-08ca-4f00-b9aa-a2772e6161fa.png" />
</p>


### 3. 2 ERD
<p align="center">
  <img width="75%" src="https://user-images.githubusercontent.com/59362257/190061600-166702f0-967c-47b8-abd4-893ecf101432.png" />
</p>  

- `user`: 사용자  
- `posts`: 게시글  
- `comments`: 댓글  
- `project`: 연구 프로젝트명  
- `category`: 카테고리명  
- `attachment`: 첨부파일  



## 4. DNS(Domain Name Service)


### 4. 1 DNS Architecture
<p align="center">
  <img width="50%" src="https://user-images.githubusercontent.com/59362257/190125010-58fb7754-e9da-4489-a260-1ec494ada0aa.png" />
</p>

도메인 이름 시스템(DNS)은 사람이 읽을 수 있는 도메인 이름(예: www.amazon.com)을 머신이 읽을 수 있는 IP 주소(예: 192.0.2.44)로 변환합니다.
[[참고자료](https://aws.amazon.com/ko/route53/what-is-dns/)]


### 4. 2 내도메인.한국
[[사이트](https://내도메인.한국)]
3차 도메인을 무료로 등록할 수 있는 사이트로 간단한 정보만을 기입하여 회원가입 후 사용 가능하며, 중요한 개인 정보를 수집하지 않는다.

![제목 없음 blur](https://user-images.githubusercontent.com/77912766/190369404-07e000cf-e930-49a0-9a83-703cd86c9771.png)


### 4. 3 SSL (Https 443 Port)
HTTPS가 아닌 HTTP 프로토콜을 사용하면 브라우저(클라이언트)와 서비스(서버) 사이에 주고 받는 데이터가 암호화 되지 않는다. 클라이언트와 서버가 데이터를 주고 받는 네트워크 경로는 매우 복잡한데 이 과정에서 누군가(해커)가 데이터를 훔쳐보는 일은 어렵지 않다. 따라서 네트워크 구간에서 주고받는 데이터는 반드시 암호화 하여 데이터가 노출되더라도 무슨 내용인지 알수 없게 해야 한다.  

이러한 역할을 하는 것이 바로 HTTP에 SSL(Secured Socket Layer) 기능을 더한 HTTPS 프로토콜이다. 우리가 만든 서비스에 HTTPS 프로토콜을 제공하기 위해서는 SSL 인증서가 필요하다. SSL 인증서를 발급받아 Nginx에 적용하면 HTTPS 프로토콜로 서비스를 할수 있다.
[[참고자료](https://wikidocs.net/163144)]

- 인증서 발급을 위한 `Let's Encrypt` 설치
```bash
ubuntu@researcher1:~$ sudo apt-get install certbot
ubuntu@researcher1:~$ sudo apt-get install python3-certbot-nginx
```

- 인증서 발급
```bash
ubuntu@researcher1:~$ sudo service nginx stop
ubuntu@researcher1:~$ sudo certbot certonly --nginx -d skuniv-cgvrlab.kro.kr
ubuntu@researcher1:~$ sudo service nginx start
```

- 아래와 같이 인증서가 발급된다
```bash
/etc/letsencrypt/live/[domain]/fullchain.pem
/etc/letsencrypt/live/[domain]/privkey.pem
```

- `NginX` 설정 : `/etc/nginx/sites-available/cgvr`
```bash
server {
        listen 80;
        server_name skuniv-cgvrlab.kro.kr;

        location / {
                return 301 https://$server_name$request_uri;
        }
}

server {
        listen 443 ssl;
        server_name skuniv-cgvrlab.kro.kr;

        ssl_certificate         /etc/letsencrypt/live/skuniv-cgvrlab.kro.kr/fullchain.pem;
        ssl_certificate_key     /etc/letsencrypt/live/skuniv-cgvrlab.kro.kr/privkey.pem;

        location / {
                proxy_pass http://localhost:8080;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header Host $http_host;
        }
}
```
- `443 port` 방화벽 해제 및 `NginX` 재실행
```bash
ubuntu@researcher1:~$ sudo ufw allow 443
ubuntu@researcher1:~$ sudo service nginx restart
```

## 5. 결론


### 5. 1 결과
<p align="center">
  
</p>

### 5. 2 개발후기
- `Spring Boot` Framework와 공동개발환경 구성 등의 웹 개발 관련지식의 틀을 잡을 수 있는 좋은 기회였다.
- 처음 사용해보는 Framework로 개발초기에 어려움을 겪었으나, 개발을 진행하며 새로운 기술을 접하고 지식을 쌓는 계기가 되었다.
- 팀 프로젝트 규모의 개발과정에서 다양한 오류와 요구사항을 해결하며, `Spring Boot`의 아키텍처, 서비스, 패키지 구조에 대해 더 자세하고 분명하게 이해할 수 있었다.
- `Mustache`를 Template Engine으로 채택하여 사용하면서, 레퍼런스 자료와 기능부족을 느끼며, `ThymeLeaf` 혹은 `React`를 사용했으면 하는 아쉬움이 남았다.


### 5. 3 향후계획(개선사항)
- 연구실 인수인계를 통해 지속적으로 운영할 예정
- 모바일 디바이스 사용자를 위한 스프링 모바일 활용 예정


