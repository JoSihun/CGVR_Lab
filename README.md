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
  - Controller 는 사용자가 요청한 웹페이지를 서비스하기 위해서 모델을 호출함(Manipulates)
  - Model 은 데이터베이스나 파일과 같은 데이터 소스를 제어한 후 그 결과를 반환함
  - Controller 는 Model 이 반환한 결과를 View 에 반영함(Updates)
  - 데이터가 반영된 View 가 사용자에게 보여짐(Sees)

#### 2. 1. 2 MVC Model 1   
![image](https://user-images.githubusercontent.com/74171272/190120616-011b9e75-ae6f-40b7-9d3f-2477c5620f6d.png)
- `Controller` 영역에 `View` 영역을 같이 구현하는 방식.
- `View` 와 `Controller` 를 모두 `JSP` 가 담당하는 형태를 가지므로, 구현 난이도는 쉬움
- JSP 하나에서 MVC 가 모두 이루어져, 재사용성, 가독성 하락의 문제점이 존재함 => 유지보수에 문제가 발생함   

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
- `REST(Representational State Transfer`
  - HTTP URI 를 통해 리소스를 명시하고, HTTP Method(POST, GET, PUT, DELETE)를 통해 해당 리소스에 대한 CRUD 연산을 적용하는 것을 의미함
  - 즉, 어떤 리소스에 대해 CRUD 연산을 수행하기 위해 URI 로 HTTP Method 를 사용하여 요청을 보내는 것

- `RESTful API`
  - REST 기반의 API 를 웹으로 구현한 것
  - 기본적으로 웹의 기존 기술과 HTTP 프로토콜을 그대로 활용하기 때문에 웹의 장점을 최대한 활용할 수 있는 아키텍처 스타일

### 2. 4 URL Connection




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


### 5. 2 개발후기


### 5. 3 향후계획(개선사항)
- 연구실 인수인계를 통해 지속적으로 운영할 예정






















---
---
---
---
---
---
---
---
---
---

- This image shows how drone autonomous flight machine learning works.  
- Unity ML-Agents has 5 different functions below.  
**`Initialize`, `OnEpisodeBegin`, `CollectObservations`, `OnActionReceived`, `Heuristic`.**
  - Initialize  
    Initialization such as importing Unity object information.  
  - OnEpisodeBegin  
    Initialization of location and speed information of Agent, Target, etc. at the start of an episode.  
  - CollectObservations  
    Observation of environmental information for policy making.  
  - OnActionReceived  
    Conduct actions according to the determined policy
  - Heuristic  
    Manipulated by the developer to make sure the behavior works well.
- **`Heuristic`** is excluded because it is just checking function that if actions work or not.  

<p align="center"><img width="100%" src="https://user-images.githubusercontent.com/59362257/151522584-ee581411-754b-4d61-8ef9-16a2429de2e2.png"/></p>

- First, get Environment Informations from `Environment` such as `Map Information`, `Target Position`, `Agent Position` etc.  
- Second, YOLOv4 Tiny from Agent detect objects and get detected object data, such as `size`, `position`, `distance` etc.
- Then, update the reward and modify the behavior to get better reward from `Unity ML Agent`.  
- During Learning, the learning information is transmitted to the `MonitoringUI`.  


### 2. 2 Sub Arhitecture
- This image shows how `Agent` learns from `Unity ML Agent`.

<p align="center"><img width="75%" src="https://user-images.githubusercontent.com/59362257/151524351-5fcd0a75-627f-468b-9f3e-aa6e3bc4a7e3.png"/></p>

- A Behavior is selected automatically based on reward in Communiator from Unity ML Agent.  
- The Drone performs actions and detects obstacles by two cameras.  
- After determining whether the object detected by the camera is an obstacle or a target,  
a reward is set according to the measured distance information.


### 2. 3 Stereo Camera Triangulation
- This Autonomous Flight Simulation is based on Stereo Camera Triangulation system.  

<p align="center"><img width="50%" src="https://user-images.githubusercontent.com/59362257/151525158-6cf1773f-26d3-470a-9f1c-5d689dd6b91a.png" /></p>

- 양안식 스테레오 비전 시스템은 삼각측량을 기반으로 양안식 측정 방법을 통해 목표 물체의 3차원 정보를 얻을 수 있다.  
- P는 목표지점, OL과 OR은 동일한 초점거리 카메라의 시축 센터(Visual Center), PL과 PR은 각각 목표물을 바라보는 투시점,  
xL과 xR은 카메라 이미지의 PL과 PR에 대응하는 왼쪽과 오른쪽의 수평 좌표계이다.
- 양안식 스테레오 비전 측정 방법에 따라 OL, OR, PL, PR은 각각 이미지의 좌표를 의미하며,  
  B는 두 카메라의 이격거리, f는 카메라의 초점거리, xL - xR은 disparity(d)로 정의된 시차오차,  
  D는 카메라와 목표물(P)까지의 거리로 명시했을 때, 다음의 두 식으로 정리된다(식 (1)과 (2)).  

<p align="center"><img width="50%" src="https://user-images.githubusercontent.com/59362257/151526049-ea9181f5-34e0-4a21-b69f-a8563c282f26.png" /></p>

- 식 (1)은 D와 B의 비율은 f와 xL - xR의 비율과 같으므로 이것을 D에 대해 정리한 식이다.  
- 식 (2)는 D와 W의 비율은 f와 w의 비율과 같으므로 이것을 f에 대해 정리한 식이다.  
- 이 때, w는 CCD(Charge-Coupled Device)에 의한 목표물 이미지의 폭(mm), W는 CCD에 의하여 표시될 수 있는 목표물의 크기를 의미한다.


### 2. 4 Distance Reward

<p align="left"><img width="25%" src="https://user-images.githubusercontent.com/59362257/151532019-ba710b20-156a-4d13-a2cc-74aac69e3c78.png"/></p>

- Using Triangulation to measure the distance to the detected object.  
- Normalize the measurement distance to a value of 0 to 1 by divided by the limit distance.
- If no object is detected within range, return -1.





## 3. Train

### 3. 1 Set Learning Environment
- Place 244 object-shaped obstacles evenly.  
- Environment in the form of a 1km long straight track.
- Distributed Reinforcement Learning 3,500,000 steps.  

<p align="center"><img width="75%" src="https://user-images.githubusercontent.com/59362257/151547869-3ea16d7f-e140-471e-b167-91ee1cc2b887.png" /></p>


### 3. 2 Training Data

|Observe Status|Params|Description|
|:---|---|:---|
|Agent Position|3|드론의 위치정보|
|Agent Velocity|3|드론의 속도정보|
|Agent Angular Velocity|3|드론의 각속도 정보|
|Target Position|3|목표지점의 위치정보|
|Camera Observation|50|카메라 탐지물체 거리정보|

- 62개의 상태정보
- 3가지 행동(x, y, z축 이동)
- 상태정보(62개)를 통해 행동(3가지)을 결정


### 3. 3 PPO
- The machine learning of this project is based on PPO.
- 매 Iteration마다 N개의 Actor가 T개의 timestep만큼의 데이터를 모아 학습하는 방식
- NT개의 데이터를 통해 surrogate loss를 형성하고, minibatch SGD를 적용해 학습
- K epoch에 걸쳐 반복

<p align="center"><img width="75%" src="https://user-images.githubusercontent.com/59362257/151132334-e20f7d1c-2250-476c-9056-7b5a2d4d2667.png" /></p>

- Actor-Critic  
  Actor는 상태가 주어졌을 때 행동결정, Critic은 상태의 가치를 평가
- Surrogate Loss Function  
  대리손실함수, 손실함수가 경사하강법(SGD) 기반의 최적화 알고리즘 사용하는 것이 불가능할 때, 이를 대신하는 손실함수
- SGD(Stochastic Gradient Descent)  
  확률적 경사하강법, 일부 데이터의 모음(Mini-Batch)으로 경사하강법을 수행하는 것  
  다소 부정확할 수 있으나 빠른 계산 속도를 가짐  


### 3. 4 StereoFlight.yaml
- Make `yaml` file like below.

<p align="left"><img width="25%" src="https://user-images.githubusercontent.com/59362257/151126806-635b9693-aaba-4d9d-a3c8-f150f7ab6116.png"/></p>

- Key Parameters  

|Parameter Name|Description|
|:---|:---|
|batch_size|경사하강 1회 업데이트에 사용할 경험의 수|
|learning_rate|경사하강 학습의 정도|
|epsilon|이전 정책과 새 정책 사이의 비율 크기 제한|
|max_steps|학습할 총 step 수|


### 3. 3 Training
- After set learning environment, start machine learning with `Anaconda3` like below.  
- Start Training 3,500,000 Steps.  
```anaconda3
~/ml-agents> mlagents-learn config/ppo/StereoFlight.yaml --run-id=StereoFlight
```  
<p align="center"><img width="75%" src="Images/Anaconda_LidarFlight_001.PNG" /></p>
<p align="center"><img width="75%" src="Images/Anaconda_LidarFlight_002.PNG" /></p>
<p align="center"><img width="75%" src="Images/Anaconda_LidarFlight_003.PNG" /></p>
<p align="center"><img width="75%" src="Images/Anaconda_LidarFlight_004.PNG" /></p>

- In the image above, the AutoFlight_Bak1.yaml file is recycled.  
- If you made StereoFlight.yaml file, you can use the above command.  


### 3. 4 Training Result

<p align="center"><img width="100%" src="https://user-images.githubusercontent.com/59362257/151128795-5142eab1-a63d-49a8-95ce-63a94f90699f.png" /></p>

1,500,000 ~ 2,000,000 Step부터 보상이 증가하지 않음  
- Cumulative Reward  
모든 에이전트에 대한 평균 누적 에피소드 보상
- Extrinsic Reward  
에피소드당 환경에서 받는 평균 누적 보상
- Extrinsic Value Estimate  
에이전트가 방문한 모든 상태에 대한 평균값 추정치


## 4. Run
Run 5 times of the model trained above, 100 times each for the following environments.  

### 4. 1 Test Result
- 사물 형태의 장애물 244개를 배치한 1km 길이의 일자형 트랙  

| Test |Accuracy| Time |
|:----:|:------:|:----:|
|Test 1| 90.00% |28.93s|
|Test 2| 85.00% |28.87s|
|Test 3| 88.00% |28.94s|
|Test 4| 87.00% |28.96s|
|Test 5| 88.00% |28.94s|

- Tried 100 times each.  
- Achieved about 28.93s Average Time.  
- Achieved about 87.60% Average Accuracy.  


### 4. 2 Compare to the LiDAR based system  

![image](https://user-images.githubusercontent.com/59362257/151653290-250ab707-27df-48c1-adec-66a966cb8f65.png)

| Classification |Camera| LiDAR|
|:--------------:|:----:|:----:|
|Average Accuracy|87.60%|87.20%|
|  Average Time  |28.93s|24.15s|

- 충분히 비슷한 수준의 성능 확인
- 카메라를 이용한 자율비행의 가능성 확인





## 5. Results

본 논문은 단가가 상대적으로 저렴하고 시각 정보를 이용한 확장성이 높은 카메라 기반의 PPO 강화학습을 이용한 드론의 장애물 회피 알고리즘을 제안한다. YOLOv4Tiny를 활용한 객체검출을 통해 장애물을 검출하고 스테레오 카메라의 기하학적 해석에 따른 삼각측량 거리측정에 따라 장애물을 회피하는 분산강화학습을 통해 자율비행을 구현하는 방법을 제시한다. 실험결과 카메라 기반 장애물 회피 알고리즘은 평균 정확도 87.60%, 평균 목표지점 도달시간 28.93s를 보였고, LiDAR 기반 장애물 회피 알고리즘은 평균 정확도 87.20%, 평균 목표지점 도달시간 24.15s을 보여 카메라 기반 장애물 회피 알고리즘도 높은 정확도와 평균 목표지점 도달시간을 보임을 알 수 있었다. 위의 결과를 바탕으로 장애물 회피 자율비행 강화학습에서 단가가 높은 LiDAR 센서 대신 카메라가 사용될 수 있는 가능성을 확인했다.
