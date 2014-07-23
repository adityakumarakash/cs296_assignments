/*
* Copyright (c) 2006-2009 Erin Catto http://www.box2d.org
*
* This software is provided 'as-is', without any express or implied
* warranty.  In no event will the authors be held liable for any damages
* arising from the use of this software.
* Permission is granted to anyone to use this software for any purpose,
* including commercial applications, and to alter it and redistribute it
* freely, subject to the following restrictions:
* 1. The origin of this software must not be misrepresented; you must not
* claim that you wrote the original software. If you use this software
* in a product, an acknowledgment in the product documentation would be
* appreciated but is not required.
* 2. Altered source versions must be plainly marked as such, and must not be
* misrepresented as being the original software.
* 3. This notice may not be removed or altered from any source distribution.
*/

/* 
 * Base code for CS 296 Software Systems Lab 
 * Department of Computer Science and Engineering, IIT Bombay
 * Instructor: Parag Chaudhuri
 */


#include "cs296_base.hpp"
#include "render.hpp"
#ifdef __APPLE__
	#include <GLUT/glut.h>
#else
	#include "GL/freeglut.h"
#endif
#include <cstring>

using namespace std;
#include "dominos.hpp"


namespace cs296
{ 
	//! The Default constructor for the cs296 class.
	
	//! The various objects that are build are specified here.
  dominos_t::dominos_t()
  {
    b2Body* b1;
    
     /*! \brief 
  *
  * name -shape <br>
  * datatype -b2EdgeShape <br>
  * operation -Defines an edge between two given vectors. <br>
  * value -A edge between the coordinates (-90,0) and (90,0) <br>
  * 
  * name -bd <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * value -It contains all data for ground <br>
  * 
  */
  //GROUND
    {
      b2EdgeShape shape;
      shape.Set(b2Vec2(-90.0f, 0.0f), b2Vec2(90.0f, 0.0f));
      b2BodyDef bd; 
      b1 = m_world->CreateBody(&bd); 
      b1->CreateFixture(&shape, 0.0f);
    }

          
    //Top horizontal shelf
 /*! \brief 
  *
  * name -shape <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value -This contains shape for horizontal shelf which is in shape of a box <br>
  * 
  * name -bd <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * value - Holds data for the horizontal shelf<br>
  * 
  * name -ground <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * value - This contains pointer to the horizontal shelf body<br>
  * 
  */
    {
      b2PolygonShape shape;
      shape.SetAsBox(6.0f, 0.25f);
	
      b2BodyDef bd;
      bd.position.Set(-31.0f, 30.0f);
      b2Body* ground = m_world->CreateBody(&bd);
      ground->CreateFixture(&shape, 0.0f);
    }


    //Dominos.
 /*! \brief 
  *
  * name -shape <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value - This contains shape for Dominos which is in shape of a box<br>
  * 
  * name -fd <br>
  * datatype -b2FixtureDef <br>
  * operation -Used to attach a shape to a body for collision detection <br>
  * value - Defines a fixture for Dominos<br>
  * 
  * name -bd <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * value -Contains all data for dominos <br>
  * 
  * name -body <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * value - Defines a fixture for Dominos<br>
  * 
  */
    {
      b2PolygonShape shape;
      shape.SetAsBox(0.1f, 1.0f);
	
      b2FixtureDef fd;
      fd.shape = &shape;
      fd.density = 20.0f;
      fd.friction = 0.1f;
		
		for (int i = 0; i < 10; ++i)
		{
		  b2BodyDef bd;
		  bd.type = b2_dynamicBody;
		  bd.position.Set(-35.5f + 1.0f * i, 31.25f);
		  b2Body* body = m_world->CreateBody(&bd);
		  body->CreateFixture(&fd);
		}
    }

      
    //Another horizontal shelf.
 /*! \brief 
  *
  * name -shape <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value - This contains shape for horizontal shelf which is in a shape of box<br>
  * 
  * name -bd <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * value - Holds data for the horizontal shelf<br>
  * 
  * name -ground <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * value - create fixutre for horizontal shelf <br>
  * 
  */
    {
      b2PolygonShape shape;
      shape.SetAsBox(7.0f, 0.25f, b2Vec2(-20.f,20.f), 0.0f);
	
      b2BodyDef bd;
      bd.position.Set(1.0f, 6.0f);
      b2Body* ground = m_world->CreateBody(&bd);
      ground->CreateFixture(&shape, 0.0f);
    }



    //The pendulum that knocks the dominos off
    {
      b2Body* b2;
		{
	b2PolygonShape shape;
	shape.SetAsBox(0.25f, 1.5f);
	  
	b2BodyDef bd;
	bd.position.Set(-36.5f, 28.0f);
	b2 = m_world->CreateBody(&bd);
	b2->CreateFixture(&shape, 10.0f);
      }

	
      b2Body* b4;
 /*! \brief 
  *
  * name -shape <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value - Shape for joint of pendulum<br>
  * 
  */
      {
	b2PolygonShape shape;
	shape.SetAsBox(0.25f, 0.25f);
	  
	b2BodyDef bd;
	bd.type = b2_dynamicBody;
	bd.position.Set(-40.0f, 33.0f);
	b4 = m_world->CreateBody(&bd);
	b4->CreateFixture(&shape, 2.0f);
      }

      b2RevoluteJointDef jd;
      b2Vec2 anchor;
      anchor.Set(-37.0f, 40.0f);
      jd.Initialize(b2, b4, anchor);
      m_world->CreateJoint(&jd);
    }

      
    //The train of small spheres
 /*! \brief 
  *
  * name -spherebody <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * value - body of spheres<br>
  * 
  * name -circle <br>
  * datatype -b2CircleShape <br>
  * operation -To define a Circlular shape. <br>
  * value - Shape of spheres in train<br>
  * 
  */
    {
      b2Body* spherebody;
	
      b2CircleShape circle;
      circle.m_radius = 0.5;
	
      b2FixtureDef ballfd;
      ballfd.shape = &circle;
      ballfd.density = 1.0f;
      ballfd.friction = 0.0f;
      ballfd.restitution = 0.0f;
	
		for (int i = 0; i < 10; ++i)
		{
		  b2BodyDef ballbd;
		  ballbd.type = b2_dynamicBody;
		  ballbd.position.Set(-22.2f + i*1.0, 26.6f);
		  spherebody = m_world->CreateBody(&ballbd);
		  spherebody->CreateFixture(&ballfd);
		}
	
    }

    // another sphere rolling in other direction
   {
		// my sphere for new manouvers
 /*! \brief 
  *
  * name -circ <br>
  * datatype -b2CircleShape <br>
  * operation -To define a Circlular shape. <br>
  * value - The bigger sphere<br>
  * 
  * name -balld <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * value - Body of bigger sphere<br>
  * 
  */
		{
			b2CircleShape circ;
			circ.m_radius = 1.5f;
			
			b2BodyDef balld;
			balld.type=b2_dynamicBody;
			balld.position.Set(-25.6f, 26.6f);
			
			b2FixtureDef ballf;
			ballf.shape=&circ;
			ballf.density = 20.0f;
			ballf.friction = 0.0f;
			ballf.restitution = 0.0f;
			
			b2Body* sphere;
			sphere=m_world->CreateBody(&balld);
			sphere->CreateFixture(&ballf); 
		}

		
		// the system of two platforms
		//The revolving horizontal platform
 /*! \brief 
  *
  * 
  * name -jointDef <br>
  * datatype -b2RevoluteJointDef <br>
  * operation -Defining an anchor point where the bodies are joined. <br>
  * value - Joiints the Platform<br>
  * 
  */
		{
		  b2PolygonShape shape;
		  shape.SetAsBox(2.2f, 0.2f);
		
		  b2BodyDef bd;
		  bd.position.Set(-26.9f, 14.0f);
		  bd.type = b2_dynamicBody;
		  b2Body* body = m_world->CreateBody(&bd);
		  b2FixtureDef* fd = new b2FixtureDef;
		  fd->density = 1.f;
		  fd->shape = new b2PolygonShape;
		  fd->shape = &shape;
		  body->CreateFixture(fd);

		  b2PolygonShape shape2;
		  shape2.SetAsBox(0.2f, 2.0f);
		  b2BodyDef bd2;
		  bd2.position.Set(-26.9f, 16.0f);
		  b2Body* body2 = m_world->CreateBody(&bd2);

		  b2RevoluteJointDef jointDef;
		  jointDef.bodyA = body;
		  jointDef.bodyB = body2;
		  jointDef.localAnchorA.Set(0,0);
		  jointDef.localAnchorB.Set(0,0);
		  jointDef.collideConnected = false;
		  m_world->CreateJoint(&jointDef);
		}

		// second system block
 /*! \brief 
  *
  * name -shape <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value - Containds shape of second system of blocks<br>
  * 
  * name -jointDef <br>
  * datatype -b2RevoluteJointDef <br>
  * operation -Defining an anchor point where the bodies are joined. <br>
  * 
  */
		{
		  b2PolygonShape shape;
		  shape.SetAsBox(2.2f, 0.2f);
		
		  b2BodyDef bd;
		  bd.position.Set(-31.78f, 14.0f);
		  bd.type = b2_dynamicBody;
		  b2Body* body = m_world->CreateBody(&bd);
		  b2FixtureDef* fd = new b2FixtureDef;
		  fd->density = 1.f;
		  fd->shape = new b2PolygonShape;
		  fd->shape = &shape;
		  body->CreateFixture(fd);

		  b2PolygonShape shape2;
		  shape2.SetAsBox(0.2f, 2.0f);
		  b2BodyDef bd2;
		  bd2.position.Set(-31.78f, 16.0f);
		  b2Body* body2 = m_world->CreateBody(&bd2);

		  b2RevoluteJointDef jointDef;
		  jointDef.bodyA = body;
		  jointDef.bodyB = body2;
		  jointDef.localAnchorA.Set(0,0);
		  jointDef.localAnchorB.Set(0,0);
		  jointDef.collideConnected = false;
		  m_world->CreateJoint(&jointDef);
		}

		
		//my block on the platform
 /*! \brief 
  * name -bd <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * value - Body of block<br>
  * 
  * name -body <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * 
  * name -fd <br>
  * datatype -b2FixtureDef* <br>
  * operation -Pointer to b2FixtureDev object typr. <br>
  * 
  */
		{
		  b2PolygonShape sp;
		  sp.SetAsBox(0.9f, 0.2f);
		  
		  b2BodyDef bd;
		  bd.position.Set(-29.34f, 16.05f);
		  bd.type = b2_dynamicBody;
		  
		  b2Body* body=m_world->CreateBody(&bd);
		  
		  b2FixtureDef* fd = new b2FixtureDef;
		  fd->density = 0.195f;
		  fd->shape = new b2PolygonShape;
		  fd->shape = &sp;
		  body->CreateFixture(fd);
		}

		 /*! \brief 
  *
  * name -circ <br>
  * datatype -b2CircleShape <br>
  * operation -To define a Circlular shape. <br>
  * value - Defines smaller spheres<br>
  * 
  * 
  */
		// my sphere1 for new manouvers

		{
			b2CircleShape circ;
			circ.m_radius = 0.5f;
			
			b2BodyDef balld;
			balld.type=b2_dynamicBody;
			balld.position.Set(-25.35f, 16.05f);
			
			b2FixtureDef ballf;
			ballf.shape=&circ;
			ballf.density = 0.1f;
			ballf.friction = 0.0f;
			ballf.restitution = 0.0f;
			
			b2Body* sphere;
			sphere=m_world->CreateBody(&balld);
			sphere->CreateFixture(&ballf); 
		}

		
		// my sphere2 for new manouvers
		{
			b2CircleShape circ;
			circ.m_radius = 0.5f;
			
			b2BodyDef balld;
			balld.type=b2_dynamicBody;
			balld.position.Set(-33.28f, 16.05f);
			
			b2FixtureDef ballf;
			ballf.shape=&circ;
			ballf.density = 0.1f;
			ballf.friction = 0.0f;
			ballf.restitution = 0.0f;
			
			b2Body* sphere;
			sphere=m_world->CreateBody(&balld);
			sphere->CreateFixture(&ballf); 
		}

		
		// the motor 1 added
		{
		  b2PolygonShape shape;
		  shape.SetAsBox(1.2f, 0.1f);
		
		  b2BodyDef bd;
		  bd.position.Set(-24.9f, 21.0f);
		  bd.type = b2_dynamicBody;
		  b2Body* body = m_world->CreateBody(&bd);
		  b2FixtureDef* fd = new b2FixtureDef;
		  fd->density = 1.f;
		  fd->shape = new b2PolygonShape;
		  fd->shape = &shape;
		  body->CreateFixture(fd);

		  b2PolygonShape shape2;
		  shape2.SetAsBox(0.2f, 2.0f);
		  b2BodyDef bd2;
		  bd2.position.Set(-24.9f, 23.0f);
		  b2Body* body2 = m_world->CreateBody(&bd2);

		  b2RevoluteJointDef jointDef;
		  jointDef.bodyA = body;
		  jointDef.bodyB = body2;
		  jointDef.localAnchorA.Set(0,0);
		  jointDef.localAnchorB.Set(0,0);
		  jointDef.collideConnected = false;
		  jointDef.enableMotor=true;
		  jointDef.motorSpeed=10;
		  jointDef.maxMotorTorque=20;
		  m_world->CreateJoint(&jointDef);
		}

		
		// motor 2 added
			{
		  b2PolygonShape shape;
		  shape.SetAsBox(1.2f, 0.1f);
		
		  b2BodyDef bd;
		  bd.position.Set(-36.75f, 22.0f);
		  bd.type = b2_dynamicBody;
		  b2Body* body = m_world->CreateBody(&bd);
		  b2FixtureDef *fd = new b2FixtureDef;
		  fd->density = 1.f;
		  fd->shape = new b2PolygonShape;
		  fd->shape = &shape;
		  body->CreateFixture(fd);

		  b2PolygonShape shape2;
		  shape2.SetAsBox(0.2f, 2.0f);
		  b2BodyDef bd2;
		  bd2.position.Set(-36.75f, 24.0f);
		  b2Body* body2 = m_world->CreateBody(&bd2);

		  b2RevoluteJointDef jointDef;
		  jointDef.bodyA = body;
		  jointDef.bodyB = body2;
		  jointDef.localAnchorA.Set(0,0);
		  jointDef.localAnchorB.Set(0,0);
		  jointDef.collideConnected = false;
		  jointDef.enableMotor=true;
		  jointDef.motorSpeed=-20;
		  jointDef.maxMotorTorque=200;
		  m_world->CreateJoint(&jointDef);
		}

}

    //The pulley system
 /*! \brief 
  *
  * name -fd1 <br>
  * datatype -b2FixtureDef* <br>
  * operation -Pointer to b2FixtureDev object typr. <br>
  * value -point to the horizontal edge of the open box <br>
  * 
  * name -bs1 <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value -contain all data for horizontal edge of the open box <br>
  * 
  * name -fd2 <br>
  * datatype -b2FixtureDef* <br>
  * operation -Pointer to b2FixtureDev object typr. <br>
  * value - point to the first vertical edge of the open box <br>
  * 
  * name -bs2 <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value - contain all data for the first vertical edge of the box <br>
  * 
  * name -fd3 <br>
  * datatype -b2FixtureDef* <br>
  * operation -Pointer to b2FixtureDev object typr. <br>
  * value -point to the first vertical edge of the open box <br>
  * 
  * name -bs3 <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value - contain all data for the first vertical edge of the box <br>
  * 
  * name -box1 <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * 
  * name -box2 <br>		
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * 
  * name -worldAnchorOnBody1 <br>
  * datatype -b2Vec2 <br>
  * operation -A 2D column vector. <br>
  * value - Anchor point on body 1<br>
  * 
  * name -worldAnchorOnBody2 <br>
  * datatype -b2Vec2 <br>
  * operation -A 2D column vector. <br>
  * value - Anchor point on body 2<br>
  * 
  * name -worldAnchorGround1 <br>
  * datatype -b2Vec2 <br>
  * operation -A 2D column vector. <br>
  * value - Anchor point on ground 1<br>
  * 
  * name -worldAnchorGround2 <br>
  * datatype -b2Vec2 <br>
  * operation -A 2D column vector. <br>
  * value - Anchor point on ground 2 <br>
  * 
  */
    {
      b2BodyDef* bd = new b2BodyDef;
      bd->type = b2_dynamicBody;
      bd->position.Set(-10,15);
      bd->fixedRotation = true;
      
      //The open box
      b2FixtureDef* fd1 = new b2FixtureDef;
      fd1->density = 10.0;
      fd1->friction = 0.5;
      fd1->restitution = 0.f;
      fd1->shape = new b2PolygonShape;
      b2PolygonShape bs1;
      bs1.SetAsBox(2,0.2, b2Vec2(0.f,-1.9f), 0);
      fd1->shape = &bs1;
      b2FixtureDef* fd2 = new b2FixtureDef;
      fd2->density = 10.0;
      fd2->friction = 0.5;
      fd2->restitution = 0.f;
      fd2->shape = new b2PolygonShape;
      b2PolygonShape bs2;
      bs2.SetAsBox(0.2,2, b2Vec2(2.0f,0.f), 0);
      fd2->shape = &bs2;
      b2FixtureDef* fd3 = new b2FixtureDef;
      fd3->density = 10.0;
      fd3->friction = 0.5;
      fd3->restitution = 0.f;
      fd3->shape = new b2PolygonShape;
      b2PolygonShape bs3;
      bs3.SetAsBox(0.2,2, b2Vec2(-2.0f,0.f), 0);
      fd3->shape = &bs3;
       
      b2Body* box1 = m_world->CreateBody(bd);
      box1->CreateFixture(fd1);
      box1->CreateFixture(fd2);
      box1->CreateFixture(fd3);

      //The bar
      bd->position.Set(10,15);	
      fd1->density = 34.0;	  
      b2Body* box2 = m_world->CreateBody(bd);
      box2->CreateFixture(fd1);

      // The pulley joint
      b2PulleyJointDef* myjoint = new b2PulleyJointDef();
      b2Vec2 worldAnchorOnBody1(-10, 15); // Anchor point on body 1 in world axis
      b2Vec2 worldAnchorOnBody2(10, 15); // Anchor point on body 2 in world axis
      b2Vec2 worldAnchorGround1(-10, 20); // Anchor point for ground 1 in world axis
      b2Vec2 worldAnchorGround2(10, 20); // Anchor point for ground 2 in world axis
      float32 ratio = 1.0f; // Define ratio
      myjoint->Initialize(box1, box2, worldAnchorGround1, worldAnchorGround2, box1->GetWorldCenter(), box2->GetWorldCenter(), ratio);
      m_world->CreateJoint(myjoint);
    }


    //The revolving horizontal platform
 /*! \brief 
  *
  * name -shape <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value - Define shape for revolving horizontal platform<br>
  * 
  * name -bd <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * 
  * 
  * name -shape2 <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value -Define a box like shape <br>
  * 
  * 
  * name -body2 <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * 
  * name -jointDef <br>
  * datatype -b2RevoluteJointDef <br>
  * operation -Defining an anchor point where the bodies are joined. <br>
  * 
  */
    {
      b2PolygonShape shape;
      shape.SetAsBox(2.2f, 0.2f);
	
      b2BodyDef bd;
      bd.position.Set(14.0f, 14.0f);
      bd.type = b2_dynamicBody;
      b2Body* body = m_world->CreateBody(&bd);
      b2FixtureDef* fd = new b2FixtureDef;
      fd->density = 1.f;
      fd->shape = new b2PolygonShape;
      fd->shape = &shape;
      body->CreateFixture(fd);

      b2PolygonShape shape2;
      shape2.SetAsBox(0.2f, 2.0f);
      b2BodyDef bd2;
      bd2.position.Set(14.0f, 16.0f);
      b2Body* body2 = m_world->CreateBody(&bd2);

      b2RevoluteJointDef jointDef;
      jointDef.bodyA = body;
      jointDef.bodyB = body2;
      jointDef.localAnchorA.Set(0,0);
      jointDef.localAnchorB.Set(0,0);
      jointDef.collideConnected = false;
      m_world->CreateJoint(&jointDef);
    }


    //The heavy sphere on the platform
 /*! \brief 
  *
  * name -sbody <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * 
  * name -circle <br>
  * datatype -b2CircleShape <br>
  * operation -To define a Circlular shape. <br>
  * value -Define a circle of radius 1 unit<br>
  * 
  * name -ballfd <br>
  * datatype -b2FixtureDef <br>
  * operation -Used to attach a shape to a body for collision detection <br>
  * 
  * name -ballbd <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * value - Defines a rigid body<br>
  * 
  */
    {
      b2Body* sbody;
      b2CircleShape circle;
      circle.m_radius = 1.0;
	
      b2FixtureDef ballfd;
      ballfd.shape = &circle;
      ballfd.density = 50.0f;
      ballfd.friction = 0.0f;
      ballfd.restitution = 0.0f;
      b2BodyDef ballbd;
      ballbd.type = b2_dynamicBody;
      ballbd.position.Set(14.0f, 18.0f);
      sbody = m_world->CreateBody(&ballbd);
      sbody->CreateFixture(&ballfd);
    }



    //The see-saw system at the bottom
 /*! \brief 
  *
  * name -sbody <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * value -point to the triangle for see-saw system<br>
  * 
  * name -poly <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value -Contain all data for the triangle for see-saw system <br>
  * 
  * name -vertices <br>
  * datatype -b2Vec2 <br>
  * operation -A 2D column vector. <br>
  * 
  * name -wedgefd <br>
  * datatype -b2FixtureDef <br>
  * operation -Used to attach a shape to a body for collision detection <br>
  * value - fixture to the fixed wedge above the see-saw system<br> 
  * 
  * name -wedgebd <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * value - contain all data to define the fixed wedge above the see-saw system<br>
  * 
  * name -shape <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value -Contain data for the box of see-saw symtem <br>
  * 
  * name -body <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * 
  * name -anchor <br>
  * datatype -b2Vec2 <br>
  * operation -A 2D column vector. <br>
  * 
  * name -shape2 <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value - Contains data for the box on the right side of the see-saw<br>
  * 
  */
    {
      //The triangle wedge
      b2Body* sbody;
      b2PolygonShape poly;
      b2Vec2 vertices[3];
      vertices[0].Set(-1,0);
      vertices[1].Set(1,0);
      vertices[2].Set(0,1.5);
      poly.Set(vertices, 3);
      b2FixtureDef wedgefd;
      wedgefd.shape = &poly;
      wedgefd.density = 10.0f;
      wedgefd.friction = 0.0f;
      wedgefd.restitution = 0.0f;
      b2BodyDef wedgebd;
      wedgebd.position.Set(30.0f, 0.0f);
      sbody = m_world->CreateBody(&wedgebd);
      sbody->CreateFixture(&wedgefd);

      //The plank on top of the wedge
      b2PolygonShape shape;
      shape.SetAsBox(15.0f, 0.2f);
      b2BodyDef bd2;
      bd2.position.Set(30.0f, 1.5f);
      bd2.type = b2_dynamicBody;
      b2Body* body = m_world->CreateBody(&bd2);
      b2FixtureDef* fd2 = new b2FixtureDef;
      fd2->density = 1.f;
      fd2->shape = new b2PolygonShape;
      fd2->shape = &shape;
      body->CreateFixture(fd2);

      b2RevoluteJointDef jd;
      b2Vec2 anchor;
      anchor.Set(30.0f, 1.5f);
      jd.Initialize(sbody, body, anchor);
      m_world->CreateJoint(&jd);

      //The light box on the right side of the see-saw
      b2PolygonShape shape2;
      shape2.SetAsBox(2.0f, 2.0f);
      b2BodyDef bd3;
      bd3.position.Set(44.0f, 2.0f);
      bd3.type = b2_dynamicBody;
      b2Body* body3 = m_world->CreateBody(&bd3);
      b2FixtureDef* fd3 = new b2FixtureDef;
      fd3->density = 0.01f;
      fd3->shape = new b2PolygonShape;
      fd3->shape = &shape2;
      body3->CreateFixture(fd3);
    }

    

    // the triangular pieces that fly off - the lower system - aditya
    {
		// the flying thing
 /*! \brief 
  *
  * name -shape3 <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value - To define the triangular piece that fly off<br>
  * 
  * name -shape3poly <br>
  * datatype -b2Vec2 <br>
  * operation -A 2D column vector. <br>
  * value - Contian data for the triangular piece that fly off <br>
  * 
  * name -body4 <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * 
  */
		{
			b2PolygonShape shape3;
		  b2Vec2 shape3poly[3];
		  shape3poly[0].Set(0.0f, 0.0f);
		  shape3poly[1].Set(4.0f, 4.0f);
		  shape3poly[2].Set(4.0f, 0.0f);
		  shape3.Set(shape3poly, 3);
		  
		  b2BodyDef bd4;
		  bd4.position.Set(-35.0f, 2.0f);
		  bd4.type = b2_dynamicBody;
		  
		  b2Body* body4 = m_world->CreateBody(&bd4);
		  
		  b2FixtureDef* fd4 = new b2FixtureDef;
		  fd4->density = 0.00f;
		  fd4->shape = new b2PolygonShape;
		  fd4->shape = &shape3;
		  
		  body4->CreateFixture(fd4);
		}

		// the traingle for arial support
 /*! \brief 
  *
  * name -shape3 <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * value - To define triangle for arial support<br>
  * 
  * name -shape3poly <br>
  * datatype -b2Vec2 <br>
  * operation -A 2D column vector. <br>
  * value - Contain data for triangle for arial support<br>
  * 
  * name -body4 <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * 
  */
		{ 
		  b2PolygonShape shape3;
			
		  b2Vec2 shape3poly[3];
		  shape3poly[0].Set(0.0f, 0.0f);
		  shape3poly[1].Set(5.0f, 1.0f);
		  shape3poly[2].Set(5.0f, 0.0f);
		  shape3.Set(shape3poly, 3);
		  
		  b2BodyDef bd4;
		  bd4.position.Set(-10.0f, 2.0f);
		  bd4.type = b2_dynamicBody;
		  
		  b2Body* body4 = m_world->CreateBody(&bd4);
		  
		  b2FixtureDef* fd4 = new b2FixtureDef;
		  fd4->density = 0.00f;
		  fd4->shape = new b2PolygonShape;
		  fd4->shape = &shape3;
		  
		  body4->CreateFixture(fd4);
		}

		// the fixed thing
 /*! \brief 
  *
  * name -bd4 <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * 
  */
		{ 
		  b2PolygonShape shape3;
			
		  b2Vec2 shape3poly[3];
		  shape3poly[0].Set(0.0f, 0.0f);
		  shape3poly[1].Set(5.0f, 2.0f);
		  shape3poly[2].Set(5.0f, 0.0f);
		  shape3.Set(shape3poly, 3);
		  
		  b2BodyDef bd4;
		  bd4.position.Set(21.0f, 3.0f);
		  
		  b2Body* body4 = m_world->CreateBody(&bd4);
		  
		  b2FixtureDef *fd4 = new b2FixtureDef;
		  fd4->density = 0.00f;
		  fd4->shape = new b2PolygonShape;
		  fd4->shape = &shape3;
		  
		  body4->CreateFixture(fd4);
		}

	}
    
 /*! \brief 
  *
  * name -shape <br>
  * datatype -b2PolygonShape <br>
  * operation -To define a convex Polygon. <br>
  * 
  * name -body1 <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * 
  * name -jd <br>
  * datatype -b2RevoluteJointDef <br>
  * operation -Defining an anchor point where the bodies are joined. <br>
  * value - Joints the cross to a revolutory object and motor is attached to it<br>
  * 
  * 
  * name -circle <br>
  * datatype -b2CircleShape <br>
  * operation -To define a Circlular shape. <br>
  * 
  * name -ballfd <br>
  * datatype -b2FixtureDef <br>
  * operation -Used to attach a shape to a body for collision detection <br>
  * value -Defines fixture for balls <br>
  * 
  * name -ballbd <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * value -Body of ball <br>
  * 
  */
	{
		b2PolygonShape shape; 
      shape.SetAsBox(.5f, 3.0f);
      b2PolygonShape shape1; 
      shape1.SetAsBox(3.0f, 0.5f);
	    //shape.Set(points,8);
	    b2Body* hwed;
	    b2Body* vwed;
		  b2FixtureDef rt;
		  rt.density = 1.0f;
		  rt.friction = 0.1f;
		  rt.restitution = 0.0f;
	
      for (int i = 0; i < 5; ++i)
		{
		  b2BodyDef rotator;
		  rotator.type = b2_dynamicBody;
		  rotator.position.Set(30.2f, 15.0f+i * 6.3);
		  hwed = m_world->CreateBody(&rotator);
		  rt.shape = &shape;
		  hwed->CreateFixture(&rt);
		   vwed = m_world->CreateBody(&rotator);
		  rt.shape = &shape1;
		  vwed->CreateFixture(&rt);
		  
		   b2WeldJointDef jd1;
		  b2Vec2 anchor;
		  anchor.Set(30.2f, 15.0f+i * 6.3);
		  jd1.Initialize(hwed,vwed, anchor);
		  m_world->CreateJoint(&jd1);
		  
		  b2PolygonShape shape1;
		  shape1.SetAsBox(0.01f, 0.01f);
		  b2BodyDef bd21;
		  bd21.position.Set(30.2f, 15.0f+i * 6.3);
		  b2Body* body1 = m_world->CreateBody(&bd21);
		 
		  
		   b2RevoluteJointDef jd;
		  jd.Initialize(hwed,body1, anchor);
		   jd.enableMotor = true;
		  jd.collideConnected=true;
		  jd.maxMotorTorque = 2000;
		  jd.motorSpeed = 10 ;
		  m_world->CreateJoint(&jd);
		  
		  
		}//End of For
		// The cicle Ball
		for (int i = 0; i < 10; i++)
		{
		 b2Body* sbody;
		 b2CircleShape circle;
		  circle.m_radius = .7;		
		  b2FixtureDef ballfd;
		  ballfd.shape = &circle;
		  ballfd.density = 0.3f;
		  ballfd.friction = 0.0f;
		  ballfd.restitution = 0.0f;
		  b2BodyDef ballbd;
		  ballbd.type = b2_dynamicBody;
		  ballbd.position.Set(29.0f, 30.0f+i*1.0f);
		  sbody = m_world->CreateBody(&ballbd);
		  sbody->CreateFixture(&ballfd);
	  }

		//UPPER COVER OF COnveyer Belt
 /*! \brief 
  *
  * name -b1 <br>
  * datatype -b2Body* <br>
  * operation -Pointer to b2Body object. <br>
  * value - points to the body of edges<br>
  * 
  * name -bd <br>
  * datatype -b2BodyDef <br>
  * operation -Holds all the data needed to construct a rigid body. <br>
  * value - Contains for all the edges of conveyer belt<br>
  * 
  * name -shape <br>
  * datatype -b2EdgeShape <br>
  * operation -Defines an edge between two given vectors. <br>
  * value - Contains the shape for the edges of conveyer belt<br>
  * 
  */
		{
			b2Body* b1;
		  b2BodyDef bd; 
		  b1 = m_world->CreateBody(&bd); 
		  b2EdgeShape shape;
		  //Side Edges 
		  shape.Set(b2Vec2(33.5f, 42.0f), b2Vec2(33.5f, 13.0f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(27.1f, 42.0f), b2Vec2(27.1f, 13.0f));
		  b1->CreateFixture(&shape, 0.0f);
		  //Lower Semi-circle
		  shape.Set(b2Vec2(27.1f,13.0f), b2Vec2(28.2f, 11.9f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(28.2f, 11.9f), b2Vec2(29.3f, 11.7f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(29.3f,11.7f), b2Vec2(30.5f, 11.5f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(30.5f, 11.5f), b2Vec2(31.3f, 11.7f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(31.3f, 11.7f), b2Vec2(32.4f, 11.9f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(32.4f, 11.9f), b2Vec2(33.5f, 13.0f));
		  b1->CreateFixture(&shape, 0.0f);
		  //Upper Semi-circle
		  shape.Set(b2Vec2(27.1f,42.0f), b2Vec2(28.2f, 43.1f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(28.2f, 43.1f), b2Vec2(29.3f, 43.8f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(29.3f,43.8f), b2Vec2(30.5f, 44.2f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(30.5f, 44.2f), b2Vec2(31.3f, 43.8f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(31.3f, 43.8f), b2Vec2(32.4f, 43.1f));
		  b1->CreateFixture(&shape, 0.0f);
		  shape.Set(b2Vec2(32.4f, 43.1f), b2Vec2(33.5f, 42.0f));
		  b1->CreateFixture(&shape, 0.0f);
	}
}



  }

  sim_t *sim = new sim_t("Dominos", dominos_t::create);
}
