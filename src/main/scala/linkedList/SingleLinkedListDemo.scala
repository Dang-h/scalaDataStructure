package linkedList

import util.control.Breaks._


object SingleLinkedListDemo {

	def main(args: Array[String]): Unit = {
		val singleLinkedList = new SingleLinkedList

		val node1 = new HeroNode(1,"宋江","及时雨")
		val node2 = new HeroNode(2,"小卢","玉麒麟")
		val node3 = new HeroNode(3,"吴用","智多星")

		singleLinkedList.addByOrder(node2)
		singleLinkedList.addByOrder(node3)
		singleLinkedList.addByOrder(node1)

		singleLinkedList.list()
	}
}


class SingleLinkedList {

	/*
	创建列表【直接在列表末尾加入】：
	  1、 创建头节点head，不存放有效数据，只用于标识链表的头
	  2、 创建辅助指针（一个变量），用于定位链表尾部

	遍历列表：
	  1、 定义一个辅助指针变量
	  2、 使用while循环，当temp.newt = null时遍历结束
	  3、 让temp指向有效节点

	修改列表：
	  1、 定义辅助变量用于查找相应节点
	 */

	//编写单链表类

	//定义头节点
	private val head: HeroNode = new HeroNode(-1, "", "")

	//添加的时候考虑编号问题
	//如：插入node2，node3，node1 ==》 node1，node2，node3
	def addByOrder(heroNode: HeroNode): Unit = {

		var temp = head
		//重复No的标识
		var flag = false

		breakable {
			while (true) {

				//是否是尾部
				if (temp.next == null) {
					break()
				}
				//是否超出头节点
				if (heroNode.no < temp.next.no) {
					break()
				} else if (heroNode.no == temp.next.no) {

					//编号重复，不能添加
					flag = true
					break()
				}

				//节点后移
				temp = temp.next
			}
		}

		//根据flag做处理
		if (flag) {
			printf("No = %d 存在", heroNode)
		}else{
			heroNode.next = temp.next
			temp.next = heroNode
		}
	}

	def isEmpty(): Boolean = {
		head.next == null
	}

	//遍历列表
	def list(): Unit ={
		if (isEmpty()) {
			printf("链表为空")
			return
		}

		//定义一个辅助变量,指向头节点的下一个节点。帮助遍历
		var temp = head.next

		breakable{
			while(true){

				printf("\nNo = %d name = %s nickName = %s", temp.no, temp.name, temp.nickName)

				if (temp.next == null) {
					break()
				}

				//指针后移，输出下一节点
				temp = temp.next
			}
		}
	}
}


//定义节点类
class HeroNode(heroNo: Int, heroName: String, heroNickName: String) {

	//编号
	val no = heroNo
	//英雄名称
	var name = heroName
	//绰号
	var nickName = heroNickName
	//指针下移
	var next: HeroNode = null
}