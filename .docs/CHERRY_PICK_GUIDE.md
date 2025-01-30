# 🍒 Git 체리픽(Cherry-pick) 가이드

## 📌 체리픽이란?
> 특정 커밋만을 선택하여 다른 브랜치로 적용하는 Git 명령어입니다.  
> `merge` 또는 `rebase`와 달리, **원하는 커밋만 선택적으로 적용할 수 있는 기능**입니다.

이 가이드는 **새로운 브랜치를 생성한 후, 이후 푼 문제들만 체리픽하여 PR을 만드는 방법**을 설명합니다.

---

## 🛠️ 체리픽을 사용한 PR 작성 방법

### 1️⃣ 포크된 레포지토리에서 새로 브랜치를 생성합니다.

![브랜치 만들기 1](https://github.com/user-attachments/assets/e19cb3c8-243c-44f3-be71-6d93c7c48aca)

### ❗공용저장소의 main 브랜치를 선택해주세요.

![브랜치 만들기 2](https://github.com/user-attachments/assets/effbc5ce-832f-4177-a405-2f90c2ba4d5f)

---

### 2️⃣ 메인 브랜치에서 터미널을 열고, git log --oneline 으로 커밋 기록을 확인합니다.
```sh
# 커밋 로그를 확인할 수 있습니다.
git log --oneline
```
🔹 **커밋 해시 거꾸로 보기**  
```
git log --oneline --reverse
```

![깃 로그 보기](https://github.com/user-attachments/assets/40284055-b7fd-471a-a9d6-909fc738e816)
❗ 체리픽에는 커밋 해시가 필요하기 때문에, 왼쪽의 커밋 해시를 기억해 두시면 됩니다.
<br>
❗ 해당 가이드에서는 A+B와 A-B 두 문제의 커밋 해시를 이용했습니다.


---

### 3️⃣ 1번에서 만든 브랜치로 체크아웃 하고, 원하는 커밋을 체리픽합니다.
```sh
# 브랜치 체크아웃하기
git checkout <1번에서 생성한 브랜치 이름>

# 원하는 커밋을 체리픽으로 적용
git cherry-pick <커밋 해시1> <커밋 해시2> <커밋 해시3>
```

![체크아웃 및 체리 픽 하기](https://github.com/user-attachments/assets/14b37349-d429-49b7-80e4-c1606ab08eb9)

📌 **예시**
```
git checkout cherry-pick-guide
git cherry-pick abc1234 def5678 ghi9012
```

---

### 4️⃣ 체리픽한 브랜치로 push 합니다.
```sh
# 브랜치 푸시
git push origin <1번에서 생성한 브랜치 이름>
```

📌 **예시**
```
git push origin cherry-pick-guide
```

🔹 **잘 푸시된 것을 확인할 수 있습니다.**  
![체리픽 확인](https://github.com/user-attachments/assets/60b426f9-ac6f-4507-89f1-b933115f33fc)

---

### 5️⃣ 체리픽한 브랜치에서 공용 저장소에 PR하시면 원하는 문제만 PR에 올리실 수 있습니다.

### ❗❗❗대상 브랜치가 자신의 브랜치인지 꼭 확인해주세요❗❗❗
![PR](https://github.com/user-attachments/assets/a800ddd1-126d-4e04-867c-c3df95f0a91a)
