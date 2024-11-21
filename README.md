# ddmusic
this is build for ddmusic-pc application ，we'd developed this app with Java

![image](https://github.com/StickPoint/ddmusic/assets/46984923/bfec2b03-8160-4dd7-9dc3-a1a97a9aa3c1)

![image](https://github.com/StickPoint/ddmusic/assets/46984923/6230f944-aae3-4147-bbd5-9334736c6623)

This project is based on the latest version of JDK17

The service relies on the music backend. Previously, I used a cross domain request proxy based on NextJS to request various music websites in China. Therefore, I need to write a music backend service here. This is very 

simple for me, and I can write it anytime. If you have no doubts about the backend part, I can start writing backend services when I find a job later. I will draw the backend service architecture according to the software 

development process, discuss it with everyone, and then start writing

This project is currently based on JDK17, and the UI part combines FXML and CSS.

The packaging is done using JLink and Jpackage methods

There is a module info file among them, and the dependencies that need to be introduced must support module info, otherwise they cannot be introduced.

When this project was launched, it relied on a configuration file located in China, a remote YAML configuration file. The advantage of doing this is that if one day I release this open-source version of the demo to a 

public group, I can control the interface source information that my player depends on at any time to prevent infringement of others' music copyrights, because the development environment is only used for testing 

purposes. The remote YAML address is:

https://gitee.com/fntp_admin/ddmusic-properties/raw/master/application-dev.yaml

![image](https://github.com/user-attachments/assets/30e62e7c-29ed-4e9f-9e8c-dde10e3aacc8)


Api：Project from another brother in the group api-project：https://github.com/GitHub-ZC/Wp_music?tab=readme-ov-file

shortly , u could use docker: docker pull fntp/myapi:0.0.1

this docker-image I wrote for creating front-api-server quickly, may only be helpful in China!

I have excerpted a simple YAML parsing code from Baidu. As it only serves a temporary purpose, there is no need to pay attention to it.

U can write with the branch named dev-2023, it's the latest

Good Luck
